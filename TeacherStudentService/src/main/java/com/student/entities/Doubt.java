package com.student.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "doubts")
@Setter
@Getter
public class Doubt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long doubtId;

    private String title;
    private String description;
    
    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private DoubtStatus status;
    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;   

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher; 
    private LocalDateTime createdOn;

   
}

