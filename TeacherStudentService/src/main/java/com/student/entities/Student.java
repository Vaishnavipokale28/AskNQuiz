package com.student.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.student.entities.Doubt;


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

    private String studentName;
    private String email;
    private String password;


    private LocalDate admissionDate;
    private Long courseId;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Doubt> doubts;



    
}
