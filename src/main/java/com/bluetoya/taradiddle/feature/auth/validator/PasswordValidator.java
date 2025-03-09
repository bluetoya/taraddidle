package com.bluetoya.taradiddle.feature.auth.validator;

import static com.bluetoya.taradiddle.common.util.RegexUtil.hasLowerCase;
import static com.bluetoya.taradiddle.common.util.RegexUtil.hasNumber;
import static com.bluetoya.taradiddle.common.util.RegexUtil.hasUpperCase;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;
import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;

public class PasswordValidator implements Validator<SignInRequest> {

    @Override
    public void validate(SignInRequest request) {
        if (isShortLength(request)) {
            throw new CustomException(AuthErrorCode.NOT_ENOUGH_LENGTH_PASSWORD);
        }

        if (!isMatch(request)) {
            throw new CustomException(AuthErrorCode.UNMATCHED_PASSWORD);
        }

        if (isNameUsed(request)) {
            throw new CustomException(AuthErrorCode.PASSWORD_HOLDS_NAME);
        }

        if (!hasVariousCharacters(request)) {
            throw new CustomException(AuthErrorCode.NEED_VARIOUS_CHARACTERS);
        }
    }

    private boolean isShortLength(SignInRequest request) {
        return request.password().length() < 12;
    }

    private boolean isMatch(SignInRequest request) {
        return request.password().equals(request.confirmPassword());
    }

    private boolean isNameUsed(SignInRequest request) {
        String lowerCasePassword = request.password().toLowerCase();

        return lowerCasePassword.contains(request.firstName().toLowerCase()) || lowerCasePassword
            .contains(request.lastName().toLowerCase()) || lowerCasePassword
            .contains(request.email().substring(0, request.email().indexOf("@")))
            || lowerCasePassword.contains(request.username().toLowerCase());
    }

    private boolean hasVariousCharacters(SignInRequest request) {
        String password = request.password();

        return hasUpperCase(password) && hasLowerCase(password) && hasNumber(password);
    }
}
