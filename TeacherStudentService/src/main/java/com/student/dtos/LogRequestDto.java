package com.student.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LogRequestDto {

	private String service;
	private String message;
}
