package com.example.ncsproject.global.error;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({NcsException.class})
    public ResponseEntity<ErrorResponse> handlerException(NcsException e) {
        return new ResponseEntity<>(
                new ErrorResponse(e.getErrorCode().getMessage(), e.getErrorCode().getCode()),
                HttpStatus.valueOf(e.getErrorCode().getCode())
        );
    }
}
