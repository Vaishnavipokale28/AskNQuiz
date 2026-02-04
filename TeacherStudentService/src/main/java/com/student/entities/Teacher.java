package com.student.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "Teacher")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String name;
    private String email;
    private String password;
    private String subject;
    private Long courseId;

    @OneToMany(mappedBy = "teacher")
    private List<Doubt> doubts;

    @OneToMany(mappedBy = "teacher")
    private List<Reply> replies;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    private List<Notice> notices;


    @OneToMany(mappedBy = "teacher")
    private List<Quiz> quizzes = new ArrayList<>();

}

