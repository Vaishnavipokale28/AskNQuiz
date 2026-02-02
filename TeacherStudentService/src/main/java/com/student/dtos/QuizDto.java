package com.student.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuizDto {

	private String title;
	private String subject;
    private Integer timeLimit;
    private Integer totalMarks;
    private Long teacherId;
}
