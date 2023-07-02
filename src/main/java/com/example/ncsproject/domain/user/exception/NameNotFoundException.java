package com.example.ncsproject.domain.user.exception;

import com.example.ncsproject.global.error.ErrorCode;
import com.example.ncsproject.global.error.NcsException;

public class NameNotFoundException extends NcsException {
    public final static NameNotFoundException EXCEPTION =
            new NameNotFoundException();
    private NameNotFoundException() {
        super(ErrorCode.NAME_NOT_FOUND);
    }
}
