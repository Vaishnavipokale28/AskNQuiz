package com.student.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.dtos.ApiResponse;
import com.student.dtos.NoticeDto;
import com.student.entities.Notice;
import com.student.entities.Teacher;
import com.student.repository.NoticeRepository;
import com.student.repository.TeacherRepository;

import lombok.AllArgsConstructor;

@Service
@Transactional
@AllArgsConstructor
public class NoticeServiceImpl implements NoticeService{

	private final NoticeRepository repository;
    private final LoggerClientService logger;
    private final TeacherRepository teacherRepository;


    @Override
    public ApiResponse postNotice(NoticeDto dto) {

    	Teacher teacher = teacherRepository.findById(dto.getTeacherId())
                .orElseThrow(() -> new RuntimeException("Teacher not found"));
    	
        Notice notice = new Notice();
        notice.setTitle(dto.getTitle());
        notice.setMessage(dto.getMessage());
        notice.setTeacher(teacher);
        notice.setPostedOn(LocalDateTime.now());

        repository.save(notice);

        logger.log(
            "notice-service",
            "Notice posted by teacherId=" + dto.getTeacherId()
        );

        return new ApiResponse("Notice posted successfully", "SUCCESS");
    }
    
    
    
    @Override
    public List<Notice> getNoticesByTeacher(Long teacherId) {
        return repository.findByTeacherTeacherId(teacherId);
    }
    
    public List<NoticeDto> getAllNotices() {
        return repository.findAll()
                .stream()
                .map(n -> {
                    NoticeDto dto = new NoticeDto();
                    
                    dto.setTitle(n.getTitle());
                    dto.setMessage(n.getMessage());
                    
                    dto.setTeacherId(n.getTeacher().getTeacherId());
                    dto.setTeacherName(n.getTeacher().getName());
                    return dto;
                })
                .toList();
    }

}
