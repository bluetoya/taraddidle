package com.bluetoya.taradiddle.feature.auth.validator;

import com.bluetoya.taradiddle.feature.auth.dto.SignInRequest;

public class UsernameValidator implements Validator<SignInRequest> {

    @Override
    public void validate(SignInRequest request) {
//        username > 이메일 형식
//        username > 욕 없는지
//        username > 특수문자 제외 (이메일 앞부분만 체크)
    }
}
