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
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.auth.repository.AuthRepository;
import com.bluetoya.taradiddle.feature.auth.validator.SignInValidator;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserRepository;
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
    private final UserRepository userRepository;
    private final AuthRepository authRepository;
    private final SignInValidator validator;

    @Override
    public AuthResponse login(AuthRequest request, HttpServletResponse response) {
        validator.validateLogin(request);

        setTokens(request.email(), response);
        // TODO :: lastSignIn 업데이트

        return new AuthResponse("로그인 성공");
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        validator.validateSignIn(request);

        User user = userRepository.save(
            create(request, bCryptPasswordEncoder.encode(request.password())));

        return new SignInResponse(user);
    }

    @Override
    public AuthResponse refresh(AuthRequest authRequest, HttpServletRequest request,
        HttpServletResponse response) {
        String refreshToken = request.getHeader(CommonConstant.REFRESH_TOKEN_HEADER);

        if (Objects.nonNull(refreshToken)) {
            Auth auth = authRepository.findByUserId(authRequest.email())
                .orElseThrow(() -> new CustomException(AuthErrorCode.USER_ID_NOT_FOUND));
            if (auth.getRefreshToken().equals(refreshToken)) {
                setTokens(authRequest.userId(), response);
                return new AuthResponse("토큰 갱신 성공");
            }
        }
        throw new CustomException(AuthErrorCode.WRONG_REFRESH_TOKEN);
    }

    private void setTokens(String userId, HttpServletResponse response) {
        String accessToken = jwtProvider.generateAccessToken(userId);
        String refreshToken = jwtProvider.generateRefreshToken(userId);

        authRepository.saveRefreshToken(userId, refreshToken);

        response.setHeader(CommonConstant.AUTHENTICATION_TOKEN_HEADER,
            CommonConstant.AUTHENTICATION_TOKEN_BEARER_PREFIX + accessToken);
        response.setHeader(CommonConstant.REFRESH_TOKEN_HEADER, refreshToken);
    }
}
