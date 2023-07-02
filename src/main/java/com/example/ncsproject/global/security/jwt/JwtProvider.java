package com.example.ncsproject.global.security.jwt;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtProvider {
    void init();
    String accessTokenGenerator(String name);
    String refreshTokenGenerator(String name);
    String generateToken(String subject, Long expired, String type);
    Authentication generateAuthentication(String name);
    Claims parseToken(String token);
}
