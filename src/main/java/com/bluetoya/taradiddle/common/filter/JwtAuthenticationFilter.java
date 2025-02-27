package com.bluetoya.taradiddle.common.filter;

import com.bluetoya.taradiddle.common.config.JwtProvider;
import com.bluetoya.taradiddle.feature.auth.entity.AuthUser;
import com.bluetoya.taradiddle.feature.auth.service.AuthUserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final AuthUserService authUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        String userId = null;

        if (Objects.nonNull(token) && token.startsWith("Bearer ")) {
            token = token.substring(7);
            userId = jwtProvider.getUserIdFromToken(token);
        }

        if (Objects.nonNull(userId) && Objects.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            SecurityContextHolder.getContext().setAuthentication(getAuthentication(userId));
        }

        filterChain.doFilter(request, response);
    }

    private UsernamePasswordAuthenticationToken getAuthentication(String userId) {
        AuthUser authUser = authUserService.getAuthUser(userId);
        return new UsernamePasswordAuthenticationToken(authUser.getUserId(), authUser.getPassword(), null);
    }
}
