package com.example.ncsproject.domain.user.service;

import com.example.ncsproject.domain.user.domain.User;
import com.example.ncsproject.domain.user.presentation.dto.response.UserInfResponse;
import com.example.ncsproject.global.security.auth.Details;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@Service
public class UserServiceImpl implements UserService{

    @Override
    public UserInfResponse getUserInf() {
        Details details = (Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = details.getUser();
        DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd");
        return UserInfResponse.builder()
                .birth(dateFormat.format(user.getBirth()))
                .maxCorrect(user.getMaxCorrect())
                .name(user.getName())
                .build();
    }
}
