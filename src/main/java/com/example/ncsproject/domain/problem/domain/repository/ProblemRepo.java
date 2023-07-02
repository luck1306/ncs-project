package com.example.ncsproject.domain.problem.domain.repository;

import com.example.ncsproject.domain.problem.domain.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepo extends JpaRepository<Problem, Long> {
}
