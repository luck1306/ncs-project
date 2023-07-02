package com.example.ncsproject.domain.auth.presentation.dto.request;

import lombok.Getter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

@Getter
public class SignUpRequest {

    @NotEmpty(message = "must be input any value in accountId")
    @Pattern(regexp = "[0-9a-zA-Z]{8,20}", message = "must be following requirement")
    private String accountId;

    @NotEmpty(message = "must be input any value in name")
    private String name;

    @NotEmpty(message = "must be input any value in password")
    private String password;

    @NotEmpty(message = "must be input any value in birth")
    @Pattern(regexp = "[0-9]{6,6}", message = "must be following requirement")
    private String birth;
}
