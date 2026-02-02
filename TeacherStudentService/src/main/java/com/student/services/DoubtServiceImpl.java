package com.student.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.dtos.ApiResponse;
import com.student.dtos.DoubtDto;
import com.student.entities.Doubt;
import com.student.entities.DoubtStatus;
import com.student.entities.Student;
import com.student.entities.Teacher;
import com.student.repository.DoubtRepository;
import com.student.repository.StudentRepository;
import com.student.repository.TeacherRepository;

import lombok.AllArgsConstructor;

@Transactional
@Service
@AllArgsConstructor
public class DoubtServiceImpl implements DoubtService{

	private final DoubtRepository repository;
    private final LoggerClientService logger;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;


    // student ask doubts
    @Override
    public ApiResponse askDoubt(DoubtDto dto) {

    	  Student student = studentRepository.findById(dto.getStudentId())
                  .orElseThrow(() -> new RuntimeException("Student not found"));

          Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                  .orElseThrow(() -> new RuntimeException("Teacher not found"));
          
    	Doubt doubt = new Doubt();
        doubt.setTitle(dto.getTitle());
        doubt.setDescription(dto.getDescription());
        doubt.setStudent(student);
        doubt.setTeacher(teacher);
        doubt.setStatus(DoubtStatus.OPEN);   
        doubt.setCreatedOn(LocalDateTime.now());

        repository.save(doubt);

        logger.log(
            "doubt-service",
            "Doubt created by studentId=" + dto.getStudentId()
        );

        return new ApiResponse("Doubt submitted successfully", "SUCCESS");
    }

    // teacher reply
    @Override
    public ApiResponse replyToDoubt(Long doubtId, String replyText) {

    	Doubt doubt = repository.findById(doubtId)
                .orElseThrow(() -> new RuntimeException("Doubt not found"));

        doubt.setDescription(
            doubt.getDescription() + "\n\nTeacher Reply: " + replyText
        );
        doubt.setStatus(DoubtStatus.RESOLVED); 

        repository.save(doubt);
       

        logger.log(
            "doubt-service",
            "Reply added for doubtId=" + doubtId
        );

        return new ApiResponse("Reply added successfully", "SUCCESS");
    }

    // student view doubts
    @Override
    public List<DoubtDto> getStudentDoubts(Long studentId) {
    	return repository.findByStudent_StudentId(studentId)
                .stream()
                .map(d -> {
                    DoubtDto dto = new DoubtDto();
                    
                    dto.setTitle(d.getTitle());
                    dto.setDescription(d.getDescription());
                    dto.setStatus(d.getStatus().name());
                    dto.setCreatedOn(d.getCreatedOn());

                    dto.setStudentId(d.getStudent().getStudentId());
                    dto.setStudentName(d.getStudent().getStudentName());

                    dto.setTeacherId(d.getTeacher().getTeacherId());
                    dto.setTeacherName(d.getTeacher().getName());

                    return dto;
                })
                .toList();
    }

    //teacher view doubts
    @Override
    public List<Doubt> getTeacherDoubts(Long teacherId) {
        return repository.findByTeacher_TeacherId(teacherId);
    }
}
