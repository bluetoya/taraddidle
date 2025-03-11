package com.bluetoya.taradiddle.feature.auth.validator;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BCryptValidator {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    public void validatePassword(String password, String hashedPassword) {
        if (!bCryptPasswordEncoder.matches(password, hashedPassword)) {
            throw new CustomException(AuthErrorCode.WRONG_PASSWORD);
        }
    }

}
