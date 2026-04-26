package com.vin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {}