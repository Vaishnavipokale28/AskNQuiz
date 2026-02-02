package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entities.Quiz;
import com.student.entities.QuizStatus;

public interface QuizRepository extends JpaRepository<Quiz, Long>{

	List<Quiz> findByStatus(QuizStatus status);
}
