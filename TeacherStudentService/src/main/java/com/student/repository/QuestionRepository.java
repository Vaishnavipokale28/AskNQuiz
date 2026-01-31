package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

}
