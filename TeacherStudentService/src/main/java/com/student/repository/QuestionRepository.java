package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	// fetch questions with options in one query
    @Query("select distinct q from Question q left join fetch q.options where q.quiz.quizId = :quizId")
    List<Question> findByQuizIdWithOptions(@Param("quizId") Long quizId);
}
