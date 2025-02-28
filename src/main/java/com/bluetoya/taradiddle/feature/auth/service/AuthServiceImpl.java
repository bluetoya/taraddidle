package com.bluetoya.taradiddle.feature.auth.service;

import com.bluetoya.taradiddle.common.security.JwtProvider;
import com.bluetoya.taradiddle.feature.auth.dto.AuthDto;
import com.bluetoya.taradiddle.feature.auth.dto.LoginRequest;
import com.bluetoya.taradiddle.feature.auth.dto.LoginResponse;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInResponse;
import com.bluetoya.taradiddle.feature.auth.dto.UserDto;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.auth.repository.AuthRepository;
import com.bluetoya.taradiddle.feature.auth.validator.SignInValidator;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserRepository;
import com.bluetoya.taradiddle.feature.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;
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
        User user = userRepository.save(User.of(request, auth.getId()));

        return new SignInResponse(AuthDto.from(auth), UserDto.from(user));
    }

}
