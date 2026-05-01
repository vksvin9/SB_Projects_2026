package com.vin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
