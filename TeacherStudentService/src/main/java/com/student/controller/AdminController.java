package com.student.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.student.dtos.AdminLoginDto;
import com.student.dtos.ApiResponse;
import com.student.dtos.TeacherDto;
import com.student.entities.Student;
import com.student.entities.Teacher;
import com.student.services.AdminService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @PostMapping("/add-teacher")
    public ResponseEntity<?> addTeacher(@RequestBody TeacherDto dto) {
        try {
            return ResponseEntity.ok(adminService.addTeacher(dto));
        } catch (RuntimeException e) {
            return ResponseEntity
                    .badRequest()
                    .body(new ApiResponse(e.getMessage(), "FAILED"));
        }
    }
    
    @GetMapping("/students")
    public List<Student> getStudents() {
        return adminService.getAllStudents();
    }

    @GetMapping("/teachers")
    public List<Teacher> getTeachers() {
        return adminService.getAllTeachers();
    }
    
    @PostMapping("/login")
    public ApiResponse login(@RequestBody AdminLoginDto dto) {
        return adminService.login(dto);
    }
}
