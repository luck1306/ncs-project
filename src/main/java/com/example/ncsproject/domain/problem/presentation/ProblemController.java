package com.example.ncsproject.domain.problem.presentation;

import com.example.ncsproject.domain.problem.presentation.dto.request.GenerateProblemRequest;
import com.example.ncsproject.domain.problem.presentation.dto.response.ProblemListResponse;
import com.example.ncsproject.domain.problem.presentation.dto.response.ScoreResponse;
import com.example.ncsproject.domain.problem.service.ProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/problem")
@RestController
public class ProblemController {

    private final ProblemService service;

    @GetMapping
    public ProblemListResponse problemGetByType(@RequestParam(value = "page", required = false) Integer page,
                                                @RequestParam(value = "size", required = false) Integer size) {
        return service.problemGetByType(page, size);
    }

    @PostMapping
    public void generateProblem(@RequestBody GenerateProblemRequest request) { service.generateProblem(request); }

    @PutMapping
    public ScoreResponse updateScore(@RequestHeader(value = "score") Integer score) { return service.updateScore(score); }
}
