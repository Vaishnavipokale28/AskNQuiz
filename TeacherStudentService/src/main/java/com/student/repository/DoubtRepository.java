package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.student.entities.Doubt;

@Repository
public interface DoubtRepository extends JpaRepository<Doubt, Long> {

	List<Doubt> findByStudent_StudentId(Long studentId);

	List<Doubt> findByTeacher_TeacherId(Long teacherId);
}
