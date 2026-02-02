package com.student.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuizResponseDto {

	//for to get all quiz which are avaible for studentds
	
	private Long quizId;
    private String title;
    private String subject;
    private int timeLimit;
    private int totalMarks;
    private String status;

    private Long teacherId;
    private String teacherName;
	
}
