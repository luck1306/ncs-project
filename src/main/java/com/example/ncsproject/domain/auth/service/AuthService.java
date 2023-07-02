package com.example.ncsproject.domain.auth.service;

import com.example.ncsproject.domain.auth.presentation.dto.request.SignInRequest;
import com.example.ncsproject.domain.auth.presentation.dto.request.SignUpRequest;
import com.example.ncsproject.domain.auth.presentation.dto.response.TokenResponse;

public interface AuthService {
    void signUp(SignUpRequest request);
    TokenResponse signIn(SignInRequest request);

    void logout();
}
