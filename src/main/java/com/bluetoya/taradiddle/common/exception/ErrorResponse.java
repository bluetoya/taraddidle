package com.bluetoya.taradiddle.common.exception;

public record ErrorResponse(int status, String errorCode, String message) {

}
