package com.bluetoya.taradiddle.feature.auth.validator;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.feature.auth.dto.AuthRequest;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;
import com.bluetoya.taradiddle.feature.auth.entity.Auth;
import com.bluetoya.taradiddle.feature.auth.repository.AuthRepository;
import com.bluetoya.taradiddle.feature.user.User;
import com.bluetoya.taradiddle.feature.user.UserDao;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SignInValidator {

    private final UserDao userDao;
    private final AuthRepository authRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public void validateLogin(AuthRequest request) {
        User user = userDao.findByEmail(request.email());

        if (!bCryptPasswordEncoder.matches(request.password(), user.getAuth().getPasswordHash())) {
            throw new CustomException(AuthErrorCode.WRONG_PASSWORD);
        }
    }

    public void validateSignIn(SignInRequest request) {
        ValidatorChain<SignInRequest> chain = new ValidatorChain<>();
        chain.addValidator(new UserIdValidator(userDao));
        chain.addValidator(new PasswordValidator());

        chain.validate(request);
    }

}
