package com.student.dtos;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OptionResponseDto {
	
	//intentionally NOT sending isCorrect to student
    private Long optionId;
    private String optionText;

    
}
