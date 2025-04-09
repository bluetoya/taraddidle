package com.bluetoya.taradiddle.common.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum UserErrorCode implements ErrorCode {
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 유저입니다."),
    USER_UPDATE_FAILED(HttpStatus.NOT_FOUND, "유저 정보 갱신 중 실패하였습니다."),
    PASSWORD_UPDATE_FAILED(HttpStatus.NOT_FOUND, "패스워드 갱신 중 실패하였습니다."),
    BLOCK_USER_FAILED(HttpStatus.NOT_FOUND, "친구 차단 기능 중 에러가 발생했습니다."),
    ;

    private final HttpStatus httpStatus;
    private final String message;

}
