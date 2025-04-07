package com.bluetoya.taradiddle.feature.auth.service;

import static com.bluetoya.taradiddle.feature.auth.entity.Token.of;
import static com.bluetoya.taradiddle.feature.user.entity.User.create;

import com.bluetoya.taradiddle.common.constant.CommonConstant;
import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.common.security.JwtProvider;
import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.AuthResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.auth.entity.Token;
import com.bluetoya.taradiddle.feature.auth.validator.BCryptValidator;
import com.bluetoya.taradiddle.feature.auth.validator.SignInValidator;
import com.bluetoya.taradiddle.feature.user.entity.User;
import com.bluetoya.taradiddle.feature.user.service.UserDomainService;
import com.mongodb.client.result.UpdateResult;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final JwtProvider jwtProvider;
    private final UserDomainService userDomainService;
    private final SignInValidator validator;
    private final BCryptValidator bCryptValidator;

    @Override
    public AuthResponse login(AuthRequest request, HttpServletResponse response) {
        User user = userDomainService.findByEmail(request.email());
        bCryptValidator.validatePassword(request.password(), user.getAuth().getPasswordHash());

        setTokens(user.getId(), request.email(), response);
        UpdateResult result = userDomainService.updateLoginInfo(user.getId());

        if (result.getModifiedCount() > 0) {
            throw new CustomException(AuthErrorCode.LOGIN_FAILED);
        }

        return new AuthResponse("로그인 성공", user.getId());
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        validator.validateSignIn(request);

        User user = userDomainService.saveUser(
            create(request, bCryptValidator.encode(request.password())));

        return new SignInResponse(user);
    }

    @Override
    public AuthResponse refresh(AuthRequest authRequest, String refreshToken,
        HttpServletResponse response) {
        if (Objects.nonNull(refreshToken)) {
            Token token = userDomainService.findTokenByEmail(authRequest.email());
            if (token.getRefreshToken().equals(refreshToken)) {
                setTokens(token.getUserId(), authRequest.email(), response);
                return new AuthResponse("토큰 갱신 성공", token.getUserId());
            }
        }
        throw new CustomException(AuthErrorCode.WRONG_REFRESH_TOKEN);
    }

    @Override
    public AuthResponse logout(String userId) {
        userDomainService.logout(userId);

        return new AuthResponse("로그아웃 성공", userId);
    }

    private void setTokens(String userId, String email, HttpServletResponse response) {
        String accessToken = jwtProvider.generateAccessToken(email);
        String refreshToken = jwtProvider.generateRefreshToken(email);

        userDomainService.updateRefreshToken(of(userId, email, refreshToken));

        response.setHeader(CommonConstant.AUTHENTICATION_TOKEN_HEADER,
            CommonConstant.AUTHENTICATION_TOKEN_BEARER_PREFIX + accessToken);
        response.setHeader(CommonConstant.REFRESH_TOKEN_HEADER, refreshToken);
    }
}
