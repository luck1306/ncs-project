package com.example.ncsproject.global.config;

import com.example.ncsproject.global.security.jwt.JwtFilter;
import com.example.ncsproject.global.security.jwt.JwtProvider;
import com.example.ncsproject.global.security.jwt.properties.JwtProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsUtils;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    private final JwtProvider jwtProvider;

    private final JwtProperties jwtProperties;

    @Bean
    public PasswordEncoder passwordEncoder() { return new BCryptPasswordEncoder(); }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(AbstractHttpConfigurer::disable)
                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(e->e.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(
                        req -> req
                                .requestMatchers(CorsUtils::isPreFlightRequest).permitAll()

                                // "/auth"
                                .antMatchers(HttpMethod.POST, "/auth/sign-in").permitAll()
                                .antMatchers(HttpMethod.POST, "/auth/sign-up").permitAll()
                                .antMatchers(HttpMethod.DELETE, "/auth/logout").authenticated()

                                // "/problem"
                                .antMatchers(HttpMethod.GET, "/problem").authenticated()
                                .antMatchers(HttpMethod.POST, "/problem").authenticated()
                                .antMatchers(HttpMethod.PUT, "/problem").authenticated()

                                // "/user"
                                .antMatchers(HttpMethod.GET, "/user").authenticated()

                                .anyRequest().denyAll()
                )
                .addFilterBefore(new JwtFilter(jwtProvider, jwtProperties), UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
