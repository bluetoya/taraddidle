package com.bluetoya.taradiddle.feature.auth.service;

import com.bluetoya.taradiddle.common.security.JwtProvider;
import com.bluetoya.taradiddle.feature.auth.dto.AuthDto;
import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.AuthResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.user.UserDto;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.auth.repository.AuthRepository;
import com.bluetoya.taradiddle.feature.auth.validator.SignInValidator;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
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

        String accessToken = jwtProvider.generateAccessToken(request.userId());
        String refreshToken = jwtProvider.generateRefreshToken(request.userId());

        authRepository.saveRefreshToken(request.userId(), refreshToken);

        response.setHeader("Authorization", "Bearer " + accessToken);
        response.setHeader("X-Refresh-Token", refreshToken);

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
    public AuthResponse refresh(AuthRequest request, HttpServletResponse response) {
        // TODO :: refresh token 값 비교 후 일치하면 access token과 refresh token 재 생성해서 header에 넣어 보내기
        return null;
    }
}
