package com.student.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

// "url" points to your .NET service
@FeignClient(name = "auth-service", url = "${auth.service.url:http://localhost:5000/Auth}")
public interface AuthClient {

    @GetMapping
    String validateToken(@RequestHeader("Authorization") String token);
}