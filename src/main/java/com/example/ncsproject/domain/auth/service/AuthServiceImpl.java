package com.example.ncsproject.domain.auth.service;

import com.example.ncsproject.domain.auth.domain.Refresh;
import com.example.ncsproject.domain.auth.domain.repository.RefreshRepository;
import com.example.ncsproject.domain.auth.exception.DateParsingException;
import com.example.ncsproject.domain.auth.exception.PasswordIncorrectException;
import com.example.ncsproject.domain.auth.presentation.dto.request.SignInRequest;
import com.example.ncsproject.domain.auth.presentation.dto.request.SignUpRequest;
import com.example.ncsproject.domain.auth.presentation.dto.response.TokenResponse;
import com.example.ncsproject.domain.user.domain.User;
import com.example.ncsproject.domain.user.domain.repository.UserRepository;
import com.example.ncsproject.domain.user.exception.AlreadyExistAccountException;
import com.example.ncsproject.domain.user.exception.UserNotFoundException;
import com.example.ncsproject.global.security.auth.Details;
import com.example.ncsproject.global.security.jwt.JwtProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtProvider jwtProvider;

    private final RefreshRepository refreshRepository;

    @Override
    public void signUp(SignUpRequest request) {
        try {
            DateFormat dateFormat = new SimpleDateFormat("yyMMdd");
            String encodingPassword = passwordEncoder.encode(request.getPassword());
            Date date = dateFormat.parse(request.getBirth());
            if (!userRepository.findById(request.getAccountId()).isPresent()) {
                userRepository.save(User.builder()
                        .accountId(request.getAccountId())
                        .password(encodingPassword)
                        .name(request.getName())
                        .birth(date)
                        .build()
                );
            } else {
                throw AlreadyExistAccountException.EXCEPTION;
            }
        }
        catch (ParseException e) { throw DateParsingException.EXCEPTION; }
    }

    @Override
    public TokenResponse signIn(SignInRequest request) {
        User dbInfo = userRepository.findById(request.getAccountId()).orElseThrow(() -> UserNotFoundException.EXCEPTION);
        if (!passwordEncoder.matches(request.getPassword(), dbInfo.getPassword())) throw PasswordIncorrectException.EXCEPTION;
        return TokenResponse.builder()
                .accessToken(jwtProvider.accessTokenGenerator(dbInfo.getAccountId()))
                .refreshToken(jwtProvider.refreshTokenGenerator(dbInfo.getAccountId()))
                .build();
    }

    @Override
    public void logout() {
        // 토큰 확인하는 조건문 작성 요망 anonymous 인지 아닌지
        Details details = (Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String id = details.getUser().getAccountId();
        Refresh refresh = refreshRepository.findById(id).orElseThrow(()->UserNotFoundException.EXCEPTION);
        refreshRepository.delete(refresh);
    }
}
