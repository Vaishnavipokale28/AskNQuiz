package com.student.services;

import java.util.List;

import com.student.dtos.ApiResponse;
import com.student.dtos.QuizDto;
import com.student.dtos.QuizResponseDto;
import com.student.entities.Quiz;

public interface QuizService {

	QuizResponseDto createQuiz( QuizDto quizDto);

	 List<Quiz> getAllQuizzes();
	 
	 ApiResponse getQuizById(Long quizId);
	 
	 ApiResponse releaseQuiz(Long quizId, Long teacherId);
	 
	 List<QuizResponseDto> getReleasedQuizzes();

	ApiResponse publishQuiz(Long quizId);
	
	

}
