package com.example.ncsproject.domain.user.presentation;

import com.example.ncsproject.domain.user.presentation.dto.response.UserInfResponse;
import com.example.ncsproject.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @GetMapping
    public UserInfResponse getUserInf() { return userService.getUserInf(); }
}
