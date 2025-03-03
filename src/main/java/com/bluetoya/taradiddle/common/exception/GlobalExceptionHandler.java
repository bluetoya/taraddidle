package com.bluetoya.taradiddle.common.exception;

import com.bluetoya.taradiddle.common.exception.errorcode.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
        ErrorCode errorCode = e.getErrorCode();
        return handleExceptionResponse(errorCode);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handle(Exception e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> handleExceptionResponse(ErrorCode errorCode) {
        return ResponseEntity.badRequest()
            .body(new ErrorResponse(errorCode.getHttpStatus().value(), errorCode.name(),
                errorCode.getMessage()));
    }

}
