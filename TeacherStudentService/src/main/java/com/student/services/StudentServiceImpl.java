package com.student.services;

import com.student.dtos.ApiResponse;
import com.student.dtos.StudentDto;
import com.student.entities.Student;
import com.student.repository.StudentRepository;
import com.student.services.StudentService;
import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {


	private final LoggerClientService logger;
    private final StudentRepository repository;


    @Override
    public ApiResponse addNewStudent(StudentDto studentDto) {

        if(repository.existsByEmail(studentDto.getEmail())){
            throw new RuntimeException("Student with this email already exits");
        }

        Student student = new Student();
        student.setStudentName(studentDto.getStudentName());
        student.setEmail(studentDto.getEmail());
        student.setPassword(studentDto.getPassword());
        student.setAdmissionDate(studentDto.getAdmissionDate());
        student.setCourseId(studentDto.getCourseId());


        repository.save(student);
        System.out.println(student);
        logger.log("student-service", "Student Added: "+student);

        return new ApiResponse("Student added successfully", "SUCCESS");
        
        
    }
    
   

	@Override
	public ApiResponse updateStudent(Long studentId, StudentDto studentDto) {
		
	    Student student = repository.findById(studentId)
	            .orElseThrow(() -> new RuntimeException("Student not found"));


        student.setStudentName(studentDto.getStudentName());
        student.setEmail(studentDto.getEmail());
        student.setPassword(studentDto.getPassword());
        student.setAdmissionDate(studentDto.getAdmissionDate());
        student.setCourseId(studentDto.getCourseId());

        repository.save(student);
        
        logger.log(
        	    "student-service",
        	    "Student updated: ID=" + studentId
        	);

        return new ApiResponse("Student updated successfully", "SUCCESS");
        
  

	}


	@Override
	public ApiResponse deleteStudent(Long studentId) {
		
		Student student = repository.findById(studentId)
	            .orElseThrow(() -> new RuntimeException("Student not found"));
		
		repository.delete(student);
		
		logger.log(
	    	    "student-service",
	    	    "Student deleted: ID=" + studentId
	    	);


	    return new ApiResponse("Student deleted successfully", "SUCCESS");
	    
	    
		
	}



	@Override
	public List<StudentDto> getAllStudents() {
		return repository.findAll()
	            .stream()
	            .map(student -> {
	                StudentDto dto = new StudentDto();
	                dto.setStudentName(student.getStudentName());
	                dto.setEmail(student.getEmail());
	                dto.setAdmissionDate(student.getAdmissionDate());
	                dto.setCourseId(student.getCourseId());
	                
	                return dto;
	            })
	            .toList();
	}


	

}

