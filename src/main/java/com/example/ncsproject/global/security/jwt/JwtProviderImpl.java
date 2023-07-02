package com.example.ncsproject.global.security.jwt;

import com.example.ncsproject.domain.auth.domain.Refresh;
import com.example.ncsproject.domain.auth.domain.repository.RefreshRepository;
import com.example.ncsproject.global.security.auth.DetailsService;
import com.example.ncsproject.global.security.jwt.properties.JwtProperties;
import com.example.ncsproject.global.security.jwt.properties.TokenType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.crypto.SecretKey;
import java.util.Date;

@RequiredArgsConstructor
@Component
public class JwtProviderImpl implements JwtProvider{

    private final JwtProperties jwtProperties;

    private final DetailsService detailsService;

    private final RefreshRepository refreshRepository;

    private SecretKey secretKey;

    @PostConstruct
    @Override
    public void init() { secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.getSecret())); }

    @Override
    public String accessTokenGenerator(String name) { return generateToken(name, jwtProperties.getAccess(), TokenType.ACCESS.name()); }

    @Override
    public String refreshTokenGenerator(String name) {
        String token = generateToken(name, jwtProperties.getRefresh(), TokenType.REFRESH.name());
        refreshRepository.save(Refresh.builder()
                .accountId(name)
                .token(token)
                .ttl(jwtProperties.getRefresh() / 60)
                .build());
        return token;
    }

    @Override
    public String generateToken(String subject, Long expired, String type) {
        return Jwts.builder()
                .setSubject(subject)
                .setExpiration(new Date(System.currentTimeMillis() + (expired * 1000)))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .setIssuedAt(new Date())
                .claim("type", type)
                .compact();
    }

    @Override
    public Authentication generateAuthentication(String name) {
        UserDetails principal = detailsService.loadUserByUsername(name);
        return new UsernamePasswordAuthenticationToken(principal, null, principal.getAuthorities());
    }

    @Override
    public Claims parseToken(String token) { return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody(); }
}
