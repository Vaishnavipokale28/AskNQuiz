package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entities.QuizAttempt;

public interface QuizAttemptRepository extends JpaRepository<QuizAttempt, Long>{

	boolean existsByStudentIdAndQuiz_QuizId(Long studentId, Long quizId);
}
