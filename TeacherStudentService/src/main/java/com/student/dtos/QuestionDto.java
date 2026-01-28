package com.student.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionDto {

	private String questionText;
    private List<OptionDto> options;

}
