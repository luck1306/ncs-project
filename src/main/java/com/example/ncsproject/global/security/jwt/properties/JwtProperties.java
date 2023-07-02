package com.example.ncsproject.global.security.jwt.properties;

import io.jsonwebtoken.io.Encoders;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

import java.nio.charset.StandardCharsets;

@Getter
@ConstructorBinding
@ConfigurationProperties("jwt")
public class JwtProviderProperties {

    private final Long access;
    private final Long refresh;
    private final String secret;

    public JwtProviderProperties(Long access, Long refresh, String secret) {
        this.access = access;
        this.refresh = refresh;
        this.secret = Encoders.BASE64.encode(secret.getBytes(StandardCharsets.UTF_8));
    }
}
