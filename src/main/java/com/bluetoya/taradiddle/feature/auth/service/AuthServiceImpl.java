package com.bluetoya.taradiddle.feature.auth.service;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.common.security.JwtProvider;
import com.bluetoya.taradiddle.feature.auth.dto.LoginRequest;
import com.bluetoya.taradiddle.feature.auth.dto.LoginResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.auth.repository.AuthRepository;
import com.bluetoya.taradiddle.feature.auth.validator.PasswordValidator;
import com.bluetoya.taradiddle.feature.auth.validator.SignInValidator;
import com.bluetoya.taradiddle.feature.auth.validator.UserIdValidator;
import com.bluetoya.taradiddle.feature.auth.validator.ValidatorChain;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserDto;
import com.bluetoya.taradiddle.feature.user.UserService;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    private final UserService userService;
    private final AuthRepository authRepository;
    private final SignInValidator validator;


    @Override
    public LoginResponse login(LoginRequest request) {
        validator.validateLogin(request);

        return new LoginResponse(jwtProvider.generateAccessToken(request.userId()));
    }

    @Override
    public SignInResponse signIn(SignInRequest request) {
        validator.validateSignIn(request);

        String encryptedPassword = bCryptPasswordEncoder.encode(request.password());

        Auth auth = authRepository.save(Auth.of(request.userId(), encryptedPassword));
        User user = userService.create(new UserDto(request.username(), request.firstName(),
            request.lastName(), auth.getId()));

        return new SignInResponse(auth, user);
    }

}
