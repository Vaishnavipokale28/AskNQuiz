package com.student.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionResponseDto {
    private Long questionId;
    private String questionText;
    private List<OptionResponseDto> options;
}
