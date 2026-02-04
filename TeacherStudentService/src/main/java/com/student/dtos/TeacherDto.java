package com.student.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TeacherDto {

		private Long teacherId;
		
		@NotBlank
	    private String name;

	    @Email
	    @NotBlank
	    private String email;

	    
	    @NotBlank
	    private String subject;

	    @NotNull
	    private Long courseId;
}
