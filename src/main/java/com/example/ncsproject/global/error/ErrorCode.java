package com.example.ncsproject.global.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    NOT_FOUND_EXCEPTION("not found", 404),
    NAME_NOT_FOUND("cannot find record that search by name in db", 404),
    DATE_PARSING_CONFLICT("occur exception during parsing date", 409),
    Already_EXIST_ACCOUNT_EXCEPTION("the account is already exist in db", 400),
    USER_NOT_FOUND_EXCEPTION("cannot find user information in database", 404),
    PASSWORD_INCORRECT_EXCEPTION("password is incorrect", 409);

    private final String message;
    private final int code;
}
