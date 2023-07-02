package com.example.ncsproject.domain.auth.exception;

import com.example.ncsproject.global.error.ErrorCode;
import com.example.ncsproject.global.error.NcsException;

public class PasswordIncorrectException extends NcsException {
    public static final PasswordIncorrectException EXCEPTION = new PasswordIncorrectException();
    public PasswordIncorrectException() { super(ErrorCode.PASSWORD_INCORRECT_EXCEPTION); }
}
