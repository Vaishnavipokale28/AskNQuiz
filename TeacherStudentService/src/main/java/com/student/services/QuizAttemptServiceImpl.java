package com.student.services;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.dtos.ApiResponse;
import com.student.dtos.StartQuizDto;
import com.student.dtos.SubmitQuizDto;
import com.student.entities.AttemptStatus;
import com.student.entities.Quiz;
import com.student.entities.QuizAttempt;
import com.student.entities.QuizStatus;
import com.student.repository.QuizAttemptRepository;
import com.student.repository.QuizRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QuizAttemptServiceImpl implements QuizAttemptService{

	private final QuizAttemptRepository attemptRepository;
    private final LoggerClientService logger;
    private final QuizRepository quizRepository;

    
    @Override
    public ApiResponse startQuiz(StartQuizDto dto) {

    	 Quiz quiz = quizRepository.findById(dto.getQuizId())
    	            .orElseThrow(() -> new RuntimeException("Quiz not found"));

    	    if (quiz.getStatus() != QuizStatus.RELEASED) {
    	        throw new RuntimeException("Quiz is not released yet");
    	    }

    	    QuizAttempt attempt = new QuizAttempt();
    	    attempt.setStudentId(dto.getStudentId());
    	    attempt.setQuiz(quiz);
    	    attempt.setStatus(AttemptStatus.STARTED);
    	    attempt.setStartTime(LocalDateTime.now());

    	    attemptRepository.save(attempt);

    	    logger.log(
    	        "attempt-service",
    	        "Student " + dto.getStudentId() + " started quiz " + quiz.getQuizId()
    	    );

    	    return new ApiResponse("Quiz started successfully", "SUCCESS");
    }

    
    @Override
    public ApiResponse submitQuiz(SubmitQuizDto dto) {

        QuizAttempt attempt = attemptRepository.findById(dto.getAttemptId())
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        // 🔹 Temporary logic (marks calculation comes in Phase 5)
        int score = dto.getAnswers().size() * 5;

        attempt.setScore(score);
        attempt.setStatus(AttemptStatus.SUBMITTED);
        attempt.setEndTime(LocalDateTime.now());

        attemptRepository.save(attempt);

        logger.log(
            "attempt-service",
            "Attempt " + attempt.getAttemptId() + " submitted with score " + score
        );

        return new ApiResponse(
            "Quiz submitted successfully",
            "SUCCESS"
        );
    }
    
}
