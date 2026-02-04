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
import com.student.dtos.StudentDto;
import com.student.services.StudentService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {


    private final StudentService service;


    @PostMapping("/register")
    public ResponseEntity<?> addNewStudent(@RequestBody @Valid StudentDto studentDto) {
        try {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(service.addNewStudent(studentDto));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage(), "FAILED"));
        }
    }
    
    @PutMapping("/update/{studentId}")
    public ResponseEntity<?> updateStudent(@PathVariable Long studentId, @RequestBody @Valid StudentDto studentDto){
    	try {
    		return ResponseEntity.ok(service.updateStudent(studentId, studentDto));
    	}catch (Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage(), "FAILED"));
		}
    }


    @DeleteMapping("/delete/{studentId}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long studentId) {
        try {
            return ResponseEntity.ok(service.deleteStudent(studentId));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(e.getMessage(), "FAILED"));
        }
    }
    
    @GetMapping("/list")
    public ResponseEntity<?> listStudents(){
    	return ResponseEntity.ok(service.getAllStudents());
    }
}
