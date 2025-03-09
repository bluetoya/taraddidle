package com.bluetoya.taradiddle.feature.auth.service;

import static com.bluetoya.taradiddle.feature.auth.entity.Token.of;
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
        User user = userDomainService.findByEmail(request.email());
        validator.validateLogin(request, user);

        setTokens(user.getId(), request.email(), response);
        userDomainService.updateLastLoginDate(user.getId());

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
    public AuthResponse refresh(AuthRequest authRequest, String refreshToken,
        HttpServletResponse response) {
        if (Objects.nonNull(refreshToken)) {
            Token token = userDomainService.findTokenByEmail(authRequest.email());
            if (token.getRefreshToken().equals(refreshToken)) {
                setTokens(token.getUserId(), authRequest.email(), response);
                return new AuthResponse("토큰 갱신 성공");
            }
        }
        throw new CustomException(AuthErrorCode.WRONG_REFRESH_TOKEN);
    }

    @Override
    public AuthResponse logout(String userId) {
        userDomainService.logout(userId);

        return new AuthResponse("로그아웃 성공");
    }

    private void setTokens(String userId, String email, HttpServletResponse response) {
        String accessToken = jwtProvider.generateAccessToken(email);
        String refreshToken = jwtProvider.generateRefreshToken(email);

        userDomainService.updateRefreshToken(of(userId, email, refreshToken));

        response.setHeader(CommonConstant.AUTHENTICATION_TOKEN_HEADER,
            CommonConstant.AUTHENTICATION_TOKEN_BEARER_PREFIX + accessToken);
        response.setHeader(CommonConstant.REFRESH_TOKEN_HEADER, refreshToken);
        response.setHeader(CommonConstant.X_USER_ID, userId);
    }
}
