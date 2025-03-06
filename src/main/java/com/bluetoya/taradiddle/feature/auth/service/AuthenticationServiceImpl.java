package com.bluetoya.taradiddle.feature.auth.service;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.common.security.JwtProvider;
import com.bluetoya.taradiddle.feature.auth.dto.AuthDto;
import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.AuthResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.auth.repository.AuthRepository;
import com.bluetoya.taradiddle.feature.auth.validator.SignInValidator;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserDto;
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

        setTokens(request.userId(), response);

        return new AuthResponse("로그인 성공했습니다.");
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        validator.validateSignIn(request);

        String encryptedPassword = bCryptPasswordEncoder.encode(request.password());

        Auth auth = authRepository.save(Auth.of(request.userId(), encryptedPassword));
        User user = userRepository.save(User.of(request, auth.getId()));

        return new SignInResponse(AuthDto.from(auth), UserDto.from(user));
    }

    @Override
    public AuthResponse refresh(AuthRequest authRequest, HttpServletRequest request,
        HttpServletResponse response) {
        String refreshToken = request.getHeader("X-Refresh-Token");

        if (Objects.nonNull(refreshToken)) {
            Auth auth = authRepository.findByUserId(authRequest.userId())
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

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("X-Refresh-Token", refreshToken);
    }
}
