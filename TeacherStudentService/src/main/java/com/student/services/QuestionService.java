package com.student.services;

import com.student.dtos.ApiResponse;
import com.student.dtos.QuestionDto;

public interface QuestionService {

	 ApiResponse addQuestion(Long quizId, QuestionDto dto);
}
