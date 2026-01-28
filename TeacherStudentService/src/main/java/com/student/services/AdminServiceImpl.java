package com.student.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.student.dtos.AdminLoginDto;
import com.student.dtos.ApiResponse;
import com.student.dtos.TeacherDto;
import com.student.entities.Admin;
import com.student.entities.Student;
import com.student.entities.Teacher;
import com.student.repository.AdminRepository;
import com.student.repository.StudentRepository;
import com.student.repository.TeacherRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

	private final AdminRepository adminRepository;
    private final TeacherRepository teacherRepository;
    private final StudentRepository studentRepository;

    @Override
    public ApiResponse addTeacher(TeacherDto dto) {

        if (teacherRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Teacher already exists");
        }

        Teacher teacher = new Teacher();
        teacher.setName(dto.getName());
        teacher.setEmail(dto.getEmail());
        teacher.setSubject(dto.getSubject());
        teacher.setCourseId(dto.getCourseId());

        teacherRepository.save(teacher);

        return new ApiResponse("Teacher added successfully", "SUCCESS");
    }
    
    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // admin fetch all teachers
    @Override
    public List<Teacher> getAllTeachers() {
        return teacherRepository.findAll();
    }
    
    @Override
    public ApiResponse login(AdminLoginDto dto) {

        Admin admin = adminRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Invalid email"));

        if (!admin.getPassword().equals(dto.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        return new ApiResponse("Admin login successful", "SUCCESS");
    }
}
