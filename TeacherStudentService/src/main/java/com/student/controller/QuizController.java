package com.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.dtos.ApiResponse;
import com.student.dtos.QuizDto;
import com.student.services.QuizService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/quiz")
@AllArgsConstructor
public class QuizController {

	private final QuizService quizService;
	
//	POST /quiz/add
//
//POST /questions/add/{quizId} ← add all questions
//
//PUT /quiz/release/{quizId}/{teacherId}    
	//this is the flow for creating quiz 
	
	 @PostMapping("/add")
	    public ResponseEntity<?> addNewQuiz(@RequestBody @Valid QuizDto quizDto) {
	        try {
	            return ResponseEntity
	                    .status(HttpStatus.OK)
	                    .body(quizService.createQuiz(quizDto));
	        } catch (RuntimeException e) {
	            return ResponseEntity
	                    .status(HttpStatus.BAD_REQUEST)
	                    .body(new ApiResponse(e.getMessage(), "FAILED"));
	        }
	    }
	 
//	 @GetMapping("/all")
//	    public ResponseEntity<?> getAllQuizzes() {
//	        return ResponseEntity.ok(quizService.getAllQuizzes());
//	    }
	 
	 @GetMapping("/{quizId}")
	    public ResponseEntity<?> getQuizById(@PathVariable Long quizId) {
	        try {
	            return ResponseEntity.ok(quizService.getQuizById(quizId));
	        } catch (RuntimeException e) {
	            return ResponseEntity
	                    .status(HttpStatus.NOT_FOUND)
	                    .body(new ApiResponse(e.getMessage(), "FAILED"));
	        }
	    }
	 
	 @PutMapping("/release/{quizId}/{teacherId}")
	    public ResponseEntity<?> releaseQuiz(
	            @PathVariable Long quizId,
	            @PathVariable Long teacherId) {

	        try {
	            return ResponseEntity.ok(quizService.releaseQuiz(quizId, teacherId));
	        } catch (RuntimeException e) {
	            return ResponseEntity.badRequest()
	                    .body(new ApiResponse(e.getMessage(), "FAILED"));
	        }
	    }
	 
	 @GetMapping("/available")
	    public ResponseEntity<?> getAvailableQuizzes() {
	        return ResponseEntity.ok(quizService.getReleasedQuizzes());
	    }
	 
	 @PutMapping("/publish/{quizId}")
	 public ResponseEntity<?> publishQuiz(@PathVariable Long quizId) {
	     try {
	         return ResponseEntity.ok(quizService.publishQuiz(quizId));
	     } catch (RuntimeException e) {
	         return ResponseEntity.badRequest()
	                 .body(new ApiResponse(e.getMessage(), "FAILED"));
	     }
	 }

}
