package com.student.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.dtos.ApiResponse;
import com.student.dtos.OptionResponseDto;
import com.student.dtos.QuestionDto;
import com.student.dtos.QuestionResponseDto;
import com.student.entities.Option;
import com.student.entities.Question;
import com.student.entities.Quiz;
import com.student.entities.QuizStatus;
import com.student.repository.QuestionRepository;
import com.student.repository.QuizRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class QuestionServiceImpl implements QuestionService{

	
	 private final QuizRepository quizRepository;
	    private final QuestionRepository questionRepository;

	    @Override
	    public ApiResponse addQuestion(Long quizId, QuestionDto dto) {

	        Quiz quiz = quizRepository.findById(quizId)
	                .orElseThrow(() -> new RuntimeException("Quiz not found"));

	        if (quiz.getStatus() != QuizStatus.DRAFT) {
	            throw new RuntimeException("Questions can be added only when quiz is in DRAFT state");
	        }

	        
	        if (dto.getOptions().size() < 2) {
	            throw new RuntimeException("Question must have at least 2 options");
	        }

	        boolean hasCorrect = dto.getOptions()
	                .stream()
	                .anyMatch(o -> o.isCorrect());

	        if (!hasCorrect) {
	            throw new RuntimeException("At least one option must be correct");
	        }

	        Question question = new Question();
	        question.setQuestionText(dto.getQuestionText());
	        question.setQuiz(quiz);

	        List<Option> options = dto.getOptions().stream().map(o -> {
	            Option opt = new Option();
	            opt.setOptionText(o.getOptionText());
	            opt.setCorrect(o.isCorrect());
	            opt.setQuestion(question);
	            return opt;
	        }).toList();

	        question.setOptions(options);
	        questionRepository.save(question);

	        return new ApiResponse("Question added successfully", "SUCCESS");
	    }
	    
	    @Override
	    public List<QuestionResponseDto> getQuestionsByQuizId(Long quizId) {

	        // released quiz only (optional rule; can be removed if want to allow draft view)
	        Quiz quiz = quizRepository.findById(quizId)
	                .orElseThrow(() -> new RuntimeException("Quiz not found"));

	        if (quiz.getStatus() != QuizStatus.RELEASED) {
	            throw new RuntimeException("Quiz is not released yet");
	        }

	        return questionRepository.findByQuizIdWithOptions(quizId)
	                .stream()
	                .map(q -> {
	                    QuestionResponseDto qdto = new QuestionResponseDto();
	                    qdto.setQuestionId(q.getQuestionId());
	                    qdto.setQuestionText(q.getQuestionText());

	                    List<OptionResponseDto> odtos = q.getOptions()
	                            .stream()
	                            .map(o -> {
	                                OptionResponseDto odto = new OptionResponseDto();
	                                odto.setOptionId(o.getOptionId());
	                                odto.setOptionText(o.getOptionText());
	                                return odto;
	                            })
	                            .toList();

	                    qdto.setOptions(odtos);
	                    return qdto;
	                })
	                .toList();
	    }
}
