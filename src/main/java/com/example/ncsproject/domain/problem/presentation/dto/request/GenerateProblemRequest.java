package com.example.ncsproject.domain.problem.presentation.dto.request;

import com.example.ncsproject.domain.problem.domain.Problem;
import com.example.ncsproject.domain.problem.domain.enums.ProblemType;
import lombok.Getter;

@Getter
public class GenerateProblemRequest {

    private String problem;

    private String answer;

    private ProblemType type;

    public Problem toProblem() {
        return Problem.builder()
                .problem(this.problem)
                .answer(this.answer)
                .problemType(this.type)
                .build();
    }
}
