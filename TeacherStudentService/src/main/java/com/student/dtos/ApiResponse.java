package com.student.dtos;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ApiResponse {

    private LocalDateTime timestamp;
    private String msg;
    private String status;

    public ApiResponse(String msg, String status) {
        this.msg = msg;
        this.status = status;
        this.timestamp= LocalDateTime.now();
    }
}
