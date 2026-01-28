package com.student.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.dtos.ApiResponse;
import com.student.dtos.QuizDto;
import com.student.dtos.QuizResponseDto;
import com.student.entities.Quiz;
import com.student.entities.QuizStatus;
import com.student.entities.Teacher;
import com.student.repository.QuizRepository;
import com.student.repository.TeacherRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QuizServiceImpl implements QuizService{
	
	private final QuizRepository quizRepository;
	private final TeacherRepository teacherRepository;
	
	
	@Override
	public ApiResponse createQuiz(QuizDto quizDto) {
		
		 Teacher teacher = teacherRepository.findById(quizDto.getTeacherId())
	                .orElseThrow(() -> new RuntimeException("Teacher not found"));
		 
		 
		Quiz quiz = new Quiz();
        quiz.setTitle(quizDto.getTitle());
        quiz.setSubject(quizDto.getSubject());
        quiz.setTimeLimit(quizDto.getTimeLimit());
        quiz.setTotalMarks(quizDto.getTotalMarks());
        quiz.setTeacher(teacher);
        quiz.setStatus(QuizStatus.DRAFT);

        quizRepository.save(quiz);
        return new ApiResponse("Quiz Created Successfully", "Success");
	}


	 @Override
	    public List<Quiz> getAllQuizzes() {
	        return quizRepository.findAll();
	    }

	    @Override
	    public ApiResponse getQuizById(Long quizId) {
	    	
	    	Quiz quiz = quizRepository.findById(quizId)
	                .orElseThrow(() -> new RuntimeException("Quiz not found"));

	        return new ApiResponse(
	                "Quiz fetched successfully: " + quiz.getTitle(),
	                "Success"
	        );
	    }
	    
	    
	    
//	    Teacher
//	    ↓ create
//	    Quiz (DRAFT)
//	      ↓ release
//	    Quiz (RELEASED)
//	      ↓ visible
//	    Student
//	      ↓ start
//	    QuizAttempt
	    
	    
	    public ApiResponse releaseQuiz(Long quizId, Long teacherId) {

	        Quiz quiz = quizRepository.findById(quizId)
	                .orElseThrow(() -> new RuntimeException("Quiz not found"));

	        if (!quiz.getTeacher().getTeacherId().equals(teacherId)) {
	            throw new RuntimeException("You are not allowed to release this quiz");
	        }

	        if (quiz.getStatus() == QuizStatus.RELEASED) {
	            throw new RuntimeException("Quiz already released");
	        }

	        quiz.setStatus(QuizStatus.RELEASED);
	        quizRepository.save(quiz);

	        return new ApiResponse("Quiz released successfully", "SUCCESS");
	    }
	    
	    
	    
	    @Override
	    public List<QuizResponseDto> getReleasedQuizzes() {

	        return quizRepository.findByStatus(QuizStatus.RELEASED)
	                .stream()
	                .map(q -> {
	                    QuizResponseDto dto = new QuizResponseDto();
	                    dto.setQuizId(q.getQuizId());
	                    dto.setTitle(q.getTitle());
	                    dto.setSubject(q.getSubject());
	                    dto.setTimeLimit(q.getTimeLimit());
	                    dto.setTotalMarks(q.getTotalMarks());
	                    dto.setStatus(q.getStatus().name());

	                    dto.setTeacherId(q.getTeacher().getTeacherId());
	                    dto.setTeacherName(q.getTeacher().getName());

	                    return dto;
	                })
	                .toList();
	    }

	    public ApiResponse publishQuiz(Long quizId) {

	        Quiz quiz = quizRepository.findById(quizId)
	                .orElseThrow(() -> new RuntimeException("Quiz not found"));

	        if (quiz.getStatus() != QuizStatus.DRAFT) {
	            throw new RuntimeException("Quiz is already published");
	        }

	        quiz.setStatus(QuizStatus.RELEASED);
	        quizRepository.save(quiz);

	        return new ApiResponse("Quiz published successfully", "SUCCESS");
	    }

	    
}
	


