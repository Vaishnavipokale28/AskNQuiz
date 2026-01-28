package com.student.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "Replies")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long replyId;

    private String description;
    private LocalDateTime timestamp;

    @ManyToOne
    @JoinColumn(name = "doubt_id")
    private Doubt doubt;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

   
}

