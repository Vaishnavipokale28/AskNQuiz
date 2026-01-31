package com.student.dtos;

import java.time.LocalDate;

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
    private String studentName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    private String password;

    @NotNull(message = "Admission date is required")
    @PastOrPresent(message = "Admission date cannot be in future")
    private LocalDate admissionDate;

    @NotNull(message = "Course ID is required")
    private Long courseId;
}
