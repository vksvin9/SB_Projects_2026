package com.vin.repository;

import com.vin.entity.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository
        extends JpaRepository<Exam, Long> {
}