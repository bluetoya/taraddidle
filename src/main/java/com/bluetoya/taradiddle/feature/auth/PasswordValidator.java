package com.bluetoya.taradiddle.feature.auth;

import com.bluetoya.taradiddle.common.exception.CustomException;
import com.bluetoya.taradiddle.common.exception.errorcode.AuthErrorCode;

public class PasswordValidator implements Validator<SignInRequest> {

    @Override
    public void validate(SignInRequest request) {
        if (!isPasswordCorrect(request)) {
            throw new CustomException(AuthErrorCode.UNMATCHED_PASSWORD);
        }

        if (request.password().length() < 12) {
            throw new CustomException(AuthErrorCode.NOT_ENOUGH_LENGTH_PASSWORD);
        }

//        - password > 소문자, 대문자, 숫자 조합
//        - password > 이메일과 동일하면 안 됨
//        - password > 성이나 이름과 동일하면 안 됨
//        - password > 1234나 qwer 같은 연속되는 문자 블록
    }

    private boolean isPasswordCorrect(SignInRequest request) {
        return request.password().equals(request.confirmPassword());
    }
}
