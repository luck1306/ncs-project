package com.example.ncsproject.domain.problem.service;

import com.example.ncsproject.domain.problem.presentation.dto.request.GenerateProblemRequest;
import com.example.ncsproject.domain.problem.presentation.dto.response.ProblemListResponse;
import com.example.ncsproject.domain.problem.presentation.dto.response.ScoreResponse;

public interface ProblemService {

    ProblemListResponse problemGetByType(Integer page, Integer size);

    void generateProblem(GenerateProblemRequest request);

    ScoreResponse updateScore(Integer score);
}
