package com.student.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "students")
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = {"doubts"})
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studentId;

    private Long userId;   // from UserAuthService
    
    private String studentName;
    private String email;
    private String password;


    private LocalDate admissionDate;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Doubt> doubts;
    
}
