package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.student.entities.AttemptAnswer;

public interface AttemptAnswerRepository extends JpaRepository<AttemptAnswer, Long> {

    void deleteByAttempt_AttemptId(Long attemptId);

    @Query("""
           select aa from AttemptAnswer aa
           join fetch aa.question q
           join fetch aa.selectedOption so
           where aa.attempt.attemptId = :attemptId
           """)
    List<AttemptAnswer> findAllByAttemptIdWithDetails(Long attemptId);
}
