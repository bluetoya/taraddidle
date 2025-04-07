package com.bluetoya.taradiddle.common.exception.errorcode;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum AuthErrorCode implements ErrorCode {
    EMAIL_NOT_FOUND(HttpStatus.NOT_FOUND, "존재하지 않는 아이디입니다."),
    WRONG_PASSWORD(HttpStatus.NOT_FOUND, "틀린 패스워드 입니다."),
    UNMATCHED_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호가 다릅니다."),
    NOT_ENOUGH_LENGTH_PASSWORD(HttpStatus.UNAUTHORIZED, "비밀번호는 최소 12자리 이상이어야 합니다."),
    INVALID_EMAIL_FORMAT(HttpStatus.BAD_REQUEST, "유효하지 않은 이메일 형식입니다."),
    PASSWORD_HOLDS_NAME(HttpStatus.BAD_REQUEST, "패스워드에 성이나 이름을 사용할 수 없습니다."),
    NEED_VARIOUS_CHARACTERS(HttpStatus.BAD_REQUEST, "패스워드에 소문자, 대문자, 숫자가 하나씩 들어가 있어야 합니다."),
    USER_ID_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "이미 존재하는 아이디입니다."),
    WRONG_ACCESS_TOKEN(HttpStatus.UNAUTHORIZED, "존재하지 않거나 만료된 엑세스 토큰입니다."),
    WRONG_REFRESH_TOKEN(HttpStatus.UNAUTHORIZED, "존재하지 않거나 만료된 리프레시 토큰입니다."),
    LOGIN_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, "로그인을 시도하였으나 실패했습니다.");

    private final HttpStatus httpStatus;
    private final String message;

}
