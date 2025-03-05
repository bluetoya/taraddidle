package com.bluetoya.taradiddle.feature.auth.validator;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.feature.auth.dto.LoginRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.auth.repository.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignInValidator {

    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void validateLogin(LoginRequest request) {
        Auth auth = authRepository.findByUserId(request.userId())
            .orElseThrow(() -> new CustomException(AuthErrorCode.USER_ID_NOT_FOUND));

        if (!bCryptPasswordEncoder.matches(request.password(), auth.getPassword())) {
            throw new CustomException(AuthErrorCode.WRONG_PASSWORD);
        }
    }

    public void validateSignIn(SignInRequest request) {
        ValidatorChain<SignInRequest> chain = new ValidatorChain<>();
        chain.addValidator(new UserIdValidator(authRepository));
        chain.addValidator(new PasswordValidator());

        chain.validate(request);
    }

}
