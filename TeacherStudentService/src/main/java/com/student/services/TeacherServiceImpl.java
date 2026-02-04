package com.student.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.dtos.ApiResponse;
import com.student.dtos.TeacherDto;
import com.student.entities.Teacher;
import com.student.repository.TeacherRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
class TeacherServiceImpl implements TeacherService{

	private final TeacherRepository teacherRepository;
	@Override
	public ApiResponse addNewTeacher(TeacherDto teacherDto) {
		if (teacherRepository.existsByEmail(teacherDto.getEmail())) {
            throw new RuntimeException("Teacher with this email already exists");
        }

        Teacher teacher = new Teacher();
        teacher.setName(teacherDto.getName());
        teacher.setEmail(teacherDto.getEmail());
        teacher.setSubject(teacherDto.getSubject());
        teacher.setCourseId(teacherDto.getCourseId());

        teacherRepository.save(teacher);

        return new ApiResponse("Teacher added successfully", "SUCCESS");
	}
	
	//fetch all teacher using teacherDto 
	@Override
	public List<TeacherDto> getAllTeachers() {
		
		return teacherRepository.findAll()
	            .stream()
	            .map(teacher -> {
	                TeacherDto dto = new TeacherDto();
	                dto.setTeacherId(teacher.getTeacherId());
	                dto.setName(teacher.getName());
	                dto.setEmail(teacher.getEmail());
	                dto.setSubject(teacher.getSubject());
	                dto.setCourseId(teacher.getCourseId());
	                return dto;
	            })
	            .toList();
	}
	
	@Override
	public ApiResponse updateTeacher(Long teacherId, TeacherDto dto) {
		Teacher teacher = teacherRepository.findById(teacherId)
                .orElseThrow(() -> new RuntimeException("Teacher not found"));

        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setSubject(dto.getSubject());
        teacher.setCourseId(dto.getCourseId());

        teacherRepository.save(teacher);

        return new ApiResponse("Teacher updated successfully", "SUCCESS");
	}
	
	@Override
	public ApiResponse deleteTeacher(Long teacherId) {
		 Teacher teacher = teacherRepository.findById(teacherId)
	                .orElseThrow(() -> new RuntimeException("Teacher not found"));

		 teacherRepository.delete(teacher);

	        return new ApiResponse("Teacher deleted successfully", "SUCCESS");
	}

}
