package com.student.dtos;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SubmitQuizDto {

	private Long attemptId;
    private Map<Long, Long> answers; 
}
