package com.example.ncsproject.domain.auth.presentation;

import com.example.ncsproject.domain.auth.presentation.dto.request.SignInRequest;
import com.example.ncsproject.domain.auth.presentation.dto.request.SignUpRequest;
import com.example.ncsproject.domain.auth.presentation.dto.response.TokenResponse;
import com.example.ncsproject.domain.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public void signUp(@Valid @RequestBody SignUpRequest request) {
        authService.signUp(request);
    }

    @PostMapping("/sign-in")
    public TokenResponse signIn(@RequestBody SignInRequest request) { return authService.signIn(request); }

    @DeleteMapping("/logout")
    public void logout() { authService.logout(); }
}
