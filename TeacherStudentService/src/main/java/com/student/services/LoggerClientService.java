package com.student.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.student.dtos.LogRequestDto;

@Service
public class LoggerClientService {

	private final RestTemplate restTemplate = new RestTemplate();
	
	private static final String LOGGER_URL = "http://localhost:5124/api/logs";
	
	public void log(String service, String message) {
		LogRequestDto request = new LogRequestDto(service, message);
		
		try {
			restTemplate.postForObject(LOGGER_URL, request, Object.class);
		}catch(Exception e){
			System.out.println("Logger Service not Available");
		}
	}
}
