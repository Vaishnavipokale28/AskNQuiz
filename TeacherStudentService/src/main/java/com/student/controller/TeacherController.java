package com.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.dtos.ApiResponse;
import com.student.dtos.TeacherDto;
import com.student.services.TeacherService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/teachers")
@AllArgsConstructor
public class TeacherController {

	private final TeacherService teacherService;

	@PostMapping("/addTeacher")
	public ResponseEntity<?> addTeacher(@RequestBody @Valid TeacherDto teacherDto) {
		try {
			return ResponseEntity.ok(teacherService.addNewTeacher(teacherDto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "Failed"));
		}
	}

	@GetMapping("/list")
	public ResponseEntity<?> listTeachers() {
		return ResponseEntity.ok(teacherService.getAllTeachers());
	}

	@PutMapping("/edit/{teacherId}")
	public ResponseEntity<?> updateTeacher(@PathVariable Long teacherId, @RequestBody @Valid TeacherDto dto) {
		try {
			return ResponseEntity.ok(teacherService.updateTeacher(teacherId, dto));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "FAILED"));
		}
	}

	@DeleteMapping("/delete/{teacherId}")
	public ResponseEntity<?> deleteTeacher(@PathVariable Long teacherId) {
		try {
			return ResponseEntity.ok(teacherService.deleteTeacher(teacherId));
		} catch (RuntimeException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), "FAILED"));
		}
	}
}
