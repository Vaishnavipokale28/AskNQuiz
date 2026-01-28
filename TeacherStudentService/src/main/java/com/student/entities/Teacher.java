package com.student.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "teachers")
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teacherId;

    private String name;
    private String email;
    private String subject;
    private Long courseId;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Doubt> doubts;

    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Reply> replies;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Notice> notices;


    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<Quiz> quizzes = new ArrayList<>();

}

