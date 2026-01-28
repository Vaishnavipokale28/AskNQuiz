package com.student.services;

import java.util.List;

import com.student.dtos.ApiResponse;
import com.student.dtos.TeacherDto;
import com.student.entities.Teacher;

import jakarta.validation.Valid;

public interface TeacherService {

	ApiResponse addNewTeacher( TeacherDto teacherDto);

	List<TeacherDto> getAllTeachers();

	ApiResponse updateTeacher(Long teacherId,TeacherDto dto);

	ApiResponse deleteTeacher(Long teacherId);

}
