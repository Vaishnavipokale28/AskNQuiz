package com.student.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AttemptResultDto {
    private Long attemptId;
    private Long quizId;
    private Long studentId;

    private Integer score;
    private int totalQuestions;
    private int correctCount;
    private int wrongCount;

    private List<QuestionResultDto> details;
}
