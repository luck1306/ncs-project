package com.example.ncsproject.domain.problem.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ProblemListResponse {

    private List<ProblemResponse> list;

    private Long totalPage;

    @Getter
    @AllArgsConstructor
    @Builder
    public static class ProblemResponse {

        private String problem;

        private String answer;
    }
}
