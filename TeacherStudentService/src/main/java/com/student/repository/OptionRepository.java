package com.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.student.entities.Option;

public interface OptionRepository extends JpaRepository<Option, Long> {

    Optional<Option> findByOptionIdAndQuestion_QuestionId(Long optionId, Long questionId);

    @Query("select o from Option o where o.question.questionId = :questionId and o.isCorrect = true")
    Optional<Option> findCorrectOption(@Param("questionId") Long questionId);
}
