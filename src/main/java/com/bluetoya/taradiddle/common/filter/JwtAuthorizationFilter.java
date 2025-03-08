package com.bluetoya.taradiddle.common.filter;

import com.bluetoya.taradiddle.common.constant.CommonConstant;
import com.bluetoya.taradiddle.common.security.JwtProvider;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthorizationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
        @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
        throws ServletException, IOException {

        String token = getTokenFromRequest(request);

        if (Objects.nonNull(token)) {
            String userId = jwtProvider.getUserIdFromToken(token);
            if (Objects.nonNull(userId) && Objects.isNull(
                SecurityContextHolder.getContext().getAuthentication())) {
                SecurityContextHolder.getContext().setAuthentication(getAuthentication(userId));
            }
        }
        filterChain.doFilter(request, response);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader(CommonConstant.AUTHENTICATION_TOKEN_HEADER);
        if (Objects.nonNull(bearerToken) && bearerToken.startsWith(CommonConstant.AUTHENTICATION_TOKEN_BEARER_PREFIX)) {
            return bearerToken.substring(7);
        }
        return null;
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String userId) {
        return new UsernamePasswordAuthenticationToken(userId, null, null);
    }
}
