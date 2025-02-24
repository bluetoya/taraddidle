package com.bluetoya.taradiddle.common.exception.errorcode;

import org.springframework.http.HttpStatus;

public interface ErrorCode {
    String name();
    HttpStatus getHttpStatus();
    String getMessage();
}

