package com.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.dtos.ApiResponse;
import com.student.dtos.StartQuizDto;
import com.student.dtos.SubmitQuizDto;
import com.student.services.QuizAttemptService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/attempts")
@AllArgsConstructor
public class QuizAttemptController {

	private final QuizAttemptService service;
	
	//this is the flow from creating quiz to attempted
//	POST /quiz/add
//    → status = DRAFT
//
//POST /questions/add/{quizId}
//    → allowed
//
//PUT /quiz/publish/{quizId}
//    → status = RELEASED
//
//GET /quiz/available
//    → students see quiz
//
//POST /attempts/start
//    → allowed ONLY if RELEASED
//
//POST /attempts/submit
//    → submit quiz


	 @PostMapping("/start")
	    public ResponseEntity<?> startQuiz(@RequestBody StartQuizDto dto) {
	        try {
	            return ResponseEntity.ok(service.startQuiz(dto));
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest()
	                    .body(new ApiResponse(e.getMessage(), "FAILED"));
	        }
	    }

	    @PostMapping("/submit")
	    public ResponseEntity<?> submitQuiz(@RequestBody SubmitQuizDto dto) {
	        try {
	            return ResponseEntity.ok(service.submitQuiz(dto));
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest()
	                    .body(new ApiResponse(e.getMessage(), "FAILED"));
	        }
	    }
	    
	 //get full result for an attempt
	    @GetMapping("/result/{attemptId}")
	    public ResponseEntity<?> getResult(@PathVariable Long attemptId) {
	        try {
	            return ResponseEntity.ok(service.getAttemptResult(attemptId));
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest()
	                    .body(new ApiResponse(e.getMessage(), "FAILED"));
	        }
	    }

	    //get all results of a student
	    @GetMapping("/student/{studentId}")
	    public ResponseEntity<?> getStudentResults(@PathVariable Long studentId) {
	        try {
	            return ResponseEntity.ok(service.getResultsForStudent(studentId));
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest()
	                    .body(new ApiResponse(e.getMessage(), "FAILED"));
	        }
	    }
}
