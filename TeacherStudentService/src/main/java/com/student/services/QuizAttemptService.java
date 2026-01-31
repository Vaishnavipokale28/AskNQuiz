package com.student.services;

import com.student.dtos.ApiResponse;
import com.student.dtos.StartQuizDto;
import com.student.dtos.SubmitQuizDto;

public interface QuizAttemptService {

	ApiResponse startQuiz(StartQuizDto dto);

	ApiResponse submitQuiz(SubmitQuizDto dto);
}
