package com.vin.repository;

import com.vin.entity.Result;
import com.vin.entity.Student;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResultRepository
        extends JpaRepository<Result, Long> {

    Optional<Result>
    findByStudentIdAndExamId(
            Long studentId,
            Long examId);

    List<Result>
    findByStudent(Student student);

    List<Result>
    findTop10ByOrderByScoreDesc();

    long countByStatus(String status);

    List<Result>
    findAllByOrderByPercentageDesc(
            Pageable pageable);
}