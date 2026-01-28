package com.student.services;

import java.util.List;

import com.student.dtos.AdminLoginDto;
import com.student.dtos.ApiResponse;
import com.student.dtos.TeacherDto;
import com.student.entities.Student;
import com.student.entities.Teacher;

public interface AdminService {

    ApiResponse addTeacher(TeacherDto dto);
    
    List<Student> getAllStudents();

    // admin fetches all teachers
    List<Teacher> getAllTeachers();
    
    ApiResponse login(AdminLoginDto dto);
}
