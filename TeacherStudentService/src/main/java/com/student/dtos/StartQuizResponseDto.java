package com.student.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StartQuizResponseDto {
    private Long attemptId;
    private Long studentId;
    private Long quizId;
    private String status;
    private LocalDateTime startTime;
}
