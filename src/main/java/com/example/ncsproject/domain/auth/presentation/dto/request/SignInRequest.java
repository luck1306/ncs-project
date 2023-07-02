package com.example.ncsproject.domain.auth.presentation.dto.request;

import lombok.Getter;

@Getter
public class SignInRequest {

    private String accountId;

    private String password;

}
