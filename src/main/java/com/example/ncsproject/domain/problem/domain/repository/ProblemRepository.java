package com.example.ncsproject.domain.problem.domain.repository;

import com.example.ncsproject.domain.problem.domain.Problem;
import com.example.ncsproject.domain.problem.domain.enums.ProblemType;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ProblemRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Problem> findByProblemType(ProblemType type, Integer page, Integer size) {
        return em.createQuery("select p from Problem p where p.type = :type", Problem.class)
                .setParameter("type", type)
                .setFirstResult(page)
                .setMaxResults(size)
                .getResultList();
    }

    public Long countProblemByType(ProblemType type) {
        return em.createQuery("select count(p.id) from Problem p where p.type = :type", Long.class)
                .setParameter("type", type)
                .getSingleResult();

    }
}
