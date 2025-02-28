package com.bluetoya.taradiddle.feature.auth.validator;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;

public class PasswordValidator implements Validator<SignInRequest> {

    @Override
    public void validate(SignInRequest request) {
        if (!isPasswordCorrect(request)) {
            throw new CustomException(AuthErrorCode.UNMATCHED_PASSWORD);
        }

        if (!isMatch(request)) {
            throw new CustomException(AuthErrorCode.UNMATCHED_PASSWORD);
        }

        if (request.password().length() < 12) {
            throw new CustomException(AuthErrorCode.NOT_ENOUGH_LENGTH_PASSWORD);
        }

        if (isNameUsed(request)) {
            throw new CustomException(AuthErrorCode.PASSWORD_HOLDS_NAME);
        }
    }

    private boolean isPasswordCorrect(SignInRequest request) {
        return request.password().equals(request.confirmPassword());
    }

    private boolean isMatch(SignInRequest request) {
        return request.password().equals(request.confirmPassword());
    }

    private boolean isNameUsed(SignInRequest request) {
        String lowerCasePassword = request.password().toLowerCase();

        return lowerCasePassword.contains(request.firstName().toLowerCase()) || lowerCasePassword
            .contains(request.lastName().toLowerCase()) || lowerCasePassword
            .contains(request.username().substring(0, request.username().indexOf("@")));
    }
}
