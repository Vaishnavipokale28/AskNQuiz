package com.student.services;

import java.util.List;

import com.student.dtos.ApiResponse;
import com.student.dtos.QuestionDto;
import com.student.dtos.QuestionResponseDto;

public interface QuestionService {

	 ApiResponse addQuestion(Long quizId, QuestionDto dto);
	 
	 List<QuestionResponseDto> getQuestionsByQuizId(Long quizId);
}
