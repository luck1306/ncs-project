package com.example.ncsproject.global.error;

import lombok.Getter;

@Getter
public class NcsException extends RuntimeException{
    private final ErrorCode errorCode;

    public NcsException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
