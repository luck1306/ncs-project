package com.example.ncsproject.domain.problem.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ScoreResponse {

    private Integer maxScore;

    private Integer currentScore;
}
