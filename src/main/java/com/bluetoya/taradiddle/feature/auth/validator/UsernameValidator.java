package com.bluetoya.taradiddle.feature.auth.validator;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;

import java.util.regex.Pattern;

public class UsernameValidator implements Validator<SignInRequest> {
    private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@(.+)$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    @Override
    public void validate(SignInRequest request) {
        String username = request.username();
        
        if (!EMAIL_PATTERN.matcher(username).matches()) {
            throw new CustomException(AuthErrorCode.INVALID_EMAIL_FORMAT);
        }
    }
}
