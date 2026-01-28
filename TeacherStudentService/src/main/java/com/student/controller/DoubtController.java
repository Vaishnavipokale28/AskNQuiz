package com.student.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.student.dtos.ApiResponse;
import com.student.dtos.DoubtDto;
import com.student.services.DoubtService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/doubts")
@AllArgsConstructor
public class DoubtController {

	private final DoubtService service;

	@PostMapping("/ask")
	public ResponseEntity<?> askDoubt(@RequestBody DoubtDto dto) {
		try {
			return ResponseEntity.ok(service.askDoubt(dto));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), "FAILED"));
		}
	}

	@PutMapping("/reply/{doubtId}")
	public ResponseEntity<?> replyDoubt(@PathVariable Long doubtId, @RequestParam String reply) {
		try {
			return ResponseEntity.ok(service.replyToDoubt(doubtId, reply));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), "FAILED"));
		}
	}

	@GetMapping("/student/{studentId}")
	public ResponseEntity<?> studentDoubts(@PathVariable Long studentId) {
		try {
			return ResponseEntity.ok(service.getStudentDoubts(studentId));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), "FAILED"));
		}
	}

	@GetMapping("/teacher/{teacherId}")
	public ResponseEntity<?> teacherDoubts(@PathVariable Long teacherId) {
		try {
			return ResponseEntity.ok(service.getTeacherDoubts(teacherId));
		} catch (RuntimeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse(e.getMessage(), "FAILED"));
		}
	}
}
