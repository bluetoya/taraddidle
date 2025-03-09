package com.bluetoya.taradiddle.feature.auth.service;

import static com.bluetoya.taradiddle.feature.user.User.create;

import com.bluetoya.taradiddle.common.constant.CommonConstant;
import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.common.security.JwtProvider;
import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.AuthResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.auth.entity.Token;
import com.bluetoya.taradiddle.feature.auth.validator.SignInValidator;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserDomainService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    private final UserDomainService userDomainService;
    private final SignInValidator validator;

    @Override
    public AuthResponse login(AuthRequest request, HttpServletResponse response) {
        validator.validateLogin(request);

        setTokens(request.email(), response);
        userDomainService.updateLastLoginDate(request.email());

        return new AuthResponse("로그인 성공");
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        validator.validateSignIn(request);

        User user = userDomainService.saveUser(
            create(request, bCryptPasswordEncoder.encode(request.password())));

        return new SignInResponse(user);
    }

    @Override
    public AuthResponse refresh(AuthRequest authRequest, HttpServletRequest request,
        HttpServletResponse response) {
        String refreshToken = request.getHeader(CommonConstant.REFRESH_TOKEN_HEADER);

        if (Objects.nonNull(refreshToken)) {
            Token token = userDomainService.findTokenByEmail(authRequest.email());
            if (token.getRefreshToken().equals(refreshToken)) {
                setTokens(authRequest.email(), response);
                return new AuthResponse("토큰 갱신 성공");
            }
        }
        throw new CustomException(AuthErrorCode.WRONG_REFRESH_TOKEN);
    }

    private void setTokens(String email, HttpServletResponse response) {
        String accessToken = jwtProvider.generateAccessToken(email);
        String refreshToken = jwtProvider.generateRefreshToken(email);

        userDomainService.saveRefreshToken(Token.create(email, refreshToken));

        response.setHeader(CommonConstant.AUTHENTICATION_TOKEN_HEADER,
            CommonConstant.AUTHENTICATION_TOKEN_BEARER_PREFIX + accessToken);
        response.setHeader(CommonConstant.REFRESH_TOKEN_HEADER, refreshToken);
    }
}
