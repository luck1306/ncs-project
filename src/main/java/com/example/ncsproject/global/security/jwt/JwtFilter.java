package com.example.ncsproject.global.security.jwt;

import com.example.ncsproject.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtProvider jwtProvider;

    private final JwtProperties jwtProperties;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String token = extractToken(request);
        if (token != null) {
            String name = jwtProvider.parseToken(token).getSubject();
            SecurityContextHolder.getContext().setAuthentication(jwtProvider.generateAuthentication(name));
        }
        filterChain.doFilter(request, response);
    }

    private String extractToken(HttpServletRequest request) {
        String token = request.getHeader(jwtProperties.getHeader());
        if (token != null && token.startsWith(jwtProperties.getPrefix())) {
            return token.substring(jwtProperties.getPrefix().length() + 1);
        }
        return null;
    }
}
