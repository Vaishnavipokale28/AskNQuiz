package com.student.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.dtos.ApiResponse;
import com.student.dtos.AttemptResultDto;
import com.student.dtos.QuestionResultDto;
import com.student.dtos.StartQuizDto;
import com.student.dtos.StartQuizResponseDto;
import com.student.dtos.SubmitQuizDto;
import com.student.entities.AttemptAnswer;
import com.student.entities.AttemptStatus;
import com.student.entities.Option;
import com.student.entities.Question;
import com.student.entities.Quiz;
import com.student.entities.QuizAttempt;
import com.student.entities.QuizStatus;
import com.student.repository.AttemptAnswerRepository;
import com.student.repository.OptionRepository;
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
    private final OptionRepository optionRepository;
    private final AttemptAnswerRepository attemptAnswerRepository;
    
    @Override
    public StartQuizResponseDto startQuiz(StartQuizDto dto) {

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

        QuizAttempt saved = attemptRepository.save(attempt);

        logger.log(
                "attempt-service",
                "Student " + dto.getStudentId() + " started quiz " + quiz.getQuizId()
        );

        // return attemptId + details
        StartQuizResponseDto res = new StartQuizResponseDto();
        res.setAttemptId(saved.getAttemptId());
        res.setStudentId(saved.getStudentId());
        res.setQuizId(saved.getQuiz().getQuizId());
        res.setStatus(saved.getStatus().name());
        res.setStartTime(saved.getStartTime());

        return res;
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
    
    @Override
    @Transactional(readOnly = true)
    public AttemptResultDto getAttemptResult(Long attemptId) {

        QuizAttempt attempt = attemptRepository.findById(attemptId)
                .orElseThrow(() -> new RuntimeException("Attempt not found"));

        if (attempt.getStatus() != AttemptStatus.SUBMITTED) {
            throw new RuntimeException("Result is available only after submission");
        }

        List<AttemptAnswer> answers = attemptAnswerRepository.findAllByAttemptIdWithDetails(attemptId);

        int total = answers.size();
        int correct = (int) answers.stream().filter(AttemptAnswer::isCorrect).count();
        int wrong = total - correct;

        List<QuestionResultDto> details = new ArrayList<>();

        for (AttemptAnswer aa : answers) {
            Question q = aa.getQuestion();
            Option selected = aa.getSelectedOption();

            Option correctOpt = optionRepository.findCorrectOption(q.getQuestionId())
                    .orElse(null);

            QuestionResultDto d = new QuestionResultDto();
            d.setQuestionId(q.getQuestionId());
            d.setQuestionText(q.getQuestionText());

            d.setSelectedOptionId(selected.getOptionId());
            d.setSelectedOptionText(selected.getOptionText());

            if (correctOpt != null) {
                d.setCorrectOptionId(correctOpt.getOptionId());
                d.setCorrectOptionText(correctOpt.getOptionText());
            }

            d.setCorrect(aa.isCorrect());
            details.add(d);
        }

        AttemptResultDto res = new AttemptResultDto();
        res.setAttemptId(attempt.getAttemptId());
        res.setQuizId(attempt.getQuiz().getQuizId());
        res.setStudentId(attempt.getStudentId());
        res.setScore(attempt.getScore());
        res.setTotalQuestions(total);
        res.setCorrectCount(correct);
        res.setWrongCount(wrong);
        res.setDetails(details);

        return res;
    }

    @Override
    @Transactional(readOnly = true)
    public List<AttemptResultDto> getResultsForStudent(Long studentId) {
        List<QuizAttempt> attempts = attemptRepository.findByStudentIdOrderByAttemptIdDesc(studentId);
        List<AttemptResultDto> results = new ArrayList<>();
        for (QuizAttempt a : attempts) {
            if (a.getStatus() == AttemptStatus.SUBMITTED) {
                results.add(getAttemptResult(a.getAttemptId()));
            }
        }
        return results;
    }
    
}
