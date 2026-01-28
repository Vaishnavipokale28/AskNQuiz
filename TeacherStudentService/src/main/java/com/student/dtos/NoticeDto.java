package com.student.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class NoticeDto {

	private String title;
	private String message;
	private LocalDateTime postedOn;
	private Long teacherId;
	private String teacherName;
}
