package com.bluetoya.taradiddle.feature.auth.service;

import com.bluetoya.taradiddle.common.security.JwtProvider;
import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.feature.auth.dto.LoginRequest;
import com.bluetoya.taradiddle.feature.auth.repository.AuthRepository;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.auth.dto.LoginResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserDto;
import com.bluetoya.taradiddle.feature.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtProvider jwtProvider;

    private final AuthRepository authRepository;

    private final UserService userService;

    @Override
    public LoginResponse login(LoginRequest request) {
        validateLogin(request);

        return new LoginResponse(jwtProvider.generateAccessToken(request.userId()));
    }

    private void validateLogin(LoginRequest request) {
        Auth auth = authRepository.findByUserId(request.userId());

        if (!isUserExists(auth)) {
            throw new CustomException(AuthErrorCode.USER_ID_NOT_FOUND);
        }

        if (!bCryptPasswordEncoder.matches(request.password(), auth.getPassword())) {
            throw new CustomException(AuthErrorCode.WRONG_PASSWORD);
        }
    }

    private boolean isUserExists(Auth auth) {
        return Objects.nonNull(auth);
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        if (!isPasswordCorrect(request)) {
            throw new CustomException(AuthErrorCode.UNMATCHED_PASSWORD);
        }

        String encryptedPassword = bCryptPasswordEncoder.encode(request.password());

        Auth auth = authRepository.save(Auth.of(request.userId(), encryptedPassword));
        User user = userService.create(new UserDto(request.username(), request.firstName(),
            request.lastName(), auth.getId()));

        return new SignInResponse(auth, user);
    }

    private boolean isPasswordCorrect(SignInRequest request) {
        return request.password().equals(request.confirmPassword());
    }
}
