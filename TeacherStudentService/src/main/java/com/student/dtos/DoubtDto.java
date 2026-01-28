package com.student.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoubtDto {

	private String title;
    private String description;
    private Long studentId;
    private String studentName;
    private Long teacherId;
    private String teacherName;
    private String status;
    private LocalDateTime createdOn;
}
