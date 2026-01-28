package com.student.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ReplyDto {

	 @NotBlank
	    private String description;

	    @NotNull
	    private Long doubtId;

	    @NotNull
	    private Long teacherId;
}
