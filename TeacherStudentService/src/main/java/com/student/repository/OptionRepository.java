package com.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.student.entities.Option;

public interface OptionRepository extends JpaRepository<Option, Long>{

}
