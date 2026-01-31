package com.student.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "quizzes")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long quizId;

    private String title;
    private String subject;
    private int timeLimit;
    private int totalMarks;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;


    @OneToMany(mappedBy = "quiz")
    private List<Question> questions;

    @Column(length = 20)
    @Enumerated(EnumType.STRING)
    private QuizStatus status; 
    
} 



