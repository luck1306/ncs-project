package com.example.ncsproject.domain.user.exception;

import com.example.ncsproject.global.error.ErrorCode;
import com.example.ncsproject.global.error.NcsException;

public class AlreadyExistAccountException extends NcsException {
    public static final AlreadyExistAccountException EXCEPTION = new AlreadyExistAccountException();
    private AlreadyExistAccountException() { super(ErrorCode.Already_EXIST_ACCOUNT_EXCEPTION); }
}
