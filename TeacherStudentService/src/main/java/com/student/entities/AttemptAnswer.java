package com.student.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "attempt_answers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AttemptAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "attempt_id")
    private QuizAttempt attempt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne(optional = false)
    @JoinColumn(name = "selected_option_id")
    private Option selectedOption;

    private boolean correct;
}
