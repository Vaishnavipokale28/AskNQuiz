package com.student.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entities.Notice;

public interface NoticeRepository extends JpaRepository<Notice, Long>{

	
	
	List<Notice> findByTeacherTeacherId(Long teacherId);
}
