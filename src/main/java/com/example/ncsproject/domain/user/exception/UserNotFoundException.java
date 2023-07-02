package com.example.ncsproject.domain.user.exception;

import com.example.ncsproject.global.error.ErrorCode;
import com.example.ncsproject.global.error.NcsException;

public class UserNotFoundException extends NcsException {
    public static final UserNotFoundException EXCEPTION = new UserNotFoundException();
    private UserNotFoundException() { super(ErrorCode.USER_NOT_FOUND_EXCEPTION); }
}
