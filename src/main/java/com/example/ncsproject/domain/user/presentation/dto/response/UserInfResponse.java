package com.example.ncsproject.domain.user.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class UserInfResponse {

    private String birth;

    private Integer maxCorrect;

    private String name;
}
