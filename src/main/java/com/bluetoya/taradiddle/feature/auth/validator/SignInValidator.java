package com.bluetoya.taradiddle.feature.auth.validator;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserDomainService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignInValidator {

    private final UserDomainService userDomainService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void validateLogin(AuthRequest request) {
        User user = userDomainService.findByEmail(request.email());

        if (!bCryptPasswordEncoder.matches(request.password(), user.getAuth().getPasswordHash())) {
            throw new CustomException(AuthErrorCode.WRONG_PASSWORD);
        }
    }

    public void validateSignIn(SignInRequest request) {
        ValidatorChain<SignInRequest> chain = new ValidatorChain<>();
        chain.addValidator(new UserIdValidator(userDomainService));
        chain.addValidator(new PasswordValidator());

        chain.validate(request);
    }

}
