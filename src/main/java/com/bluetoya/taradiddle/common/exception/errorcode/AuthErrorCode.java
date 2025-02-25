package com.bluetoya.taradiddle.common.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    USER_ID_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다."),
    WRONG_PASSWORD(HttpStatus.NOT_FOUND, "틀린 패스워드 입니다."),
    UNMATCHED_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 다릅니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
