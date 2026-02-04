package com.student.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionResultDto {
    private Long questionId;
    private String questionText;

    private Long selectedOptionId;
    private String selectedOptionText;

    private Long correctOptionId;
    private String correctOptionText;

    private boolean correct;
}
