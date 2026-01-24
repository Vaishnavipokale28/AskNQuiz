package com.student.services;

import java.util.List;

import com.student.dtos.ApiResponse;
import com.student.dtos.NoticeDto;
import com.student.entities.Notice;

public interface NoticeService {

	ApiResponse postNotice(NoticeDto dto);

	List<NoticeDto> getAllNotices();

    List<Notice> getNoticesByTeacher(Long teacherId);
}
