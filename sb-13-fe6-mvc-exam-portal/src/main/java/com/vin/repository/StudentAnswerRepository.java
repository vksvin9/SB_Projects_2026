package com.vin.repository;

import com.vin.entity.StudentAnswer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAnswerRepository
        extends JpaRepository<StudentAnswer, Long> {
}