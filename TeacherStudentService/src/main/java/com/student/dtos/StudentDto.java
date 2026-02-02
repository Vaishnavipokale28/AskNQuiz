package com.student.dtos;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty; // Required for mapping
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentDto {

    @NotBlank(message = "Student name is required")
    @JsonProperty("Name") // Maps .NET 'Name' to 'studentName'
    private String studentName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @JsonProperty("Email") // Maps .NET 'Email' to 'email'
    private String email;

    @NotBlank(message = "Password is required")
    @JsonProperty("Password")
    private String password;

    @NotNull(message = "Admission date is required")
    @PastOrPresent(message = "Admission date cannot be in future")
    @JsonProperty("AdmissionDate") // Maps .NET 'AdmissionDate' to 'admissionDate'
    private LocalDate admissionDate;
    
    @JsonProperty("Role")
    private String role;

}