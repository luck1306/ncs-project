package com.example.ncsproject.domain.problem.service;

import com.example.ncsproject.domain.problem.domain.Problem;
import com.example.ncsproject.domain.problem.domain.enums.ProblemType;
import com.example.ncsproject.domain.problem.domain.repository.ProblemRepo;
import com.example.ncsproject.domain.problem.domain.repository.ProblemRepository;
import com.example.ncsproject.domain.problem.presentation.dto.request.GenerateProblemRequest;
import com.example.ncsproject.domain.problem.presentation.dto.response.ProblemListResponse;
import com.example.ncsproject.domain.problem.presentation.dto.response.ScoreResponse;
import com.example.ncsproject.domain.user.domain.User;
import com.example.ncsproject.domain.user.domain.repository.UserRepository;
import com.example.ncsproject.global.security.auth.Details;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProblemServiceImpl implements ProblemService{

    private final UserRepository userRepository;

    private final ProblemRepository problemRepository;

    private final ProblemRepo problemRepo;

    @Override
    public ProblemListResponse problemGetByType(Integer page, Integer size) {
        Details details = (Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ProblemType type = details.getUser().getBirth().before(new Date(347122800000L)) ? ProblemType.OLD : ProblemType.YOUNG;
        size = size == null ? 2 : size;
        page = page == null ? 0 : page;
        Long rows = problemRepository.countProblemByType(type);
        return ProblemListResponse.builder()
                .list(problemRepository.findByProblemType(type, page, size).stream()
                        .map(e->ProblemListResponse.ProblemResponse.builder()
                                .answer(e.getAnswer())
                                .problem(e.getProblem())
                                .build())
                        .collect(Collectors.toList()))
                .totalPage((rows / size) + (rows % size == 0 ? 0 : 1))
                .build();
    }


    @Override
    public void generateProblem(GenerateProblemRequest request) {
        problemRepo.save(request.toProblem());
    }


    @Override
    public ScoreResponse updateScore(Integer score) {
        Details details = (Details) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = details.getUser();
        userRepository.save(user.updateMaxCorrect(score));
        return ScoreResponse.builder()
                .currentScore(score)
                .maxScore(user.getMaxCorrect())
                .build();
    }
}
