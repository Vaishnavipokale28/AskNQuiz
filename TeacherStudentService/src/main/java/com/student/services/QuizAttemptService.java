package com.student.services;

import java.util.List;

import com.student.dtos.ApiResponse;
import com.student.dtos.AttemptResultDto;
import com.student.dtos.StartQuizDto;
import com.student.dtos.StartQuizResponseDto;
import com.student.dtos.SubmitQuizDto;

public interface QuizAttemptService {

	StartQuizResponseDto startQuiz(StartQuizDto dto);

	ApiResponse submitQuiz(SubmitQuizDto dto);
	
	AttemptResultDto getAttemptResult(Long attemptId);
	
	List<AttemptResultDto> getResultsForStudent(Long studentId);
}
