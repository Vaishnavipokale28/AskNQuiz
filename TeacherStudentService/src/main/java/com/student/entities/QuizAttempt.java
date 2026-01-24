package com.student.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "quizAttempts")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class QuizAttempt {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long attemptId;

    private Long studentId;
    
    @ManyToOne
    @JoinColumn(name = "quiz_id")
    private Quiz quiz;


    private Integer score;

    @Enumerated(EnumType.STRING)
    private AttemptStatus status;

    private LocalDateTime startTime;
    private LocalDateTime endTime;
}
