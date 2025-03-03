package com.bluetoya.taradiddle.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<T> {

    private final int code;
    private final String message;
    private final T data;

    public ApiResponse(final T data) {
        this.code = HttpStatus.OK.value();
        this.message = HttpStatus.OK.name();
        this.data = data;
    }

}
