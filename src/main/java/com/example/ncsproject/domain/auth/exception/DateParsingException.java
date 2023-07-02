package com.example.ncsproject.domain.auth.exception;

import com.example.ncsproject.global.error.ErrorCode;
import com.example.ncsproject.global.error.NcsException;

public class DateParsingException extends NcsException {
    public static final DateParsingException EXCEPTION = new DateParsingException();
    private DateParsingException() { super(ErrorCode.DATE_PARSING_CONFLICT); }
}
