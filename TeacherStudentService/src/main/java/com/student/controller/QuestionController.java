package com.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.dtos.QuestionDto;
import com.student.services.QuestionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/questions")
@AllArgsConstructor
public class QuestionController {

	 private final QuestionService service;

	    @PostMapping("/add/{quizId}")
	    public ResponseEntity<?> addQuestion(
	            @PathVariable Long quizId,
	            @RequestBody QuestionDto dto) {

	        return ResponseEntity.ok(service.addQuestion(quizId, dto));
	    }
}
