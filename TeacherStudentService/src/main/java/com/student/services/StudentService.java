package com.student.services;

import java.util.List;

import com.student.dtos.ApiResponse;
import com.student.dtos.StudentDto;


public interface StudentService {

    ApiResponse addNewStudent(StudentDto studentDto);

	ApiResponse updateStudent(Long studentId, StudentDto studentDto);

	ApiResponse deleteStudent(Long studentId);

	List<StudentDto> getAllStudents();
	
	
}
