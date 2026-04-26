package com.vin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vin.dto.CourseDTO;
import com.vin.entity.Course;
import com.vin.repo.CourseRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository courseRepository;

    public Course create(CourseDTO dto) {
        Course course = new Course();
        course.setName(dto.getName());
        return courseRepository.save(course);
    }

    public List<Course> getAll() {
        return courseRepository.findAll();
    }
}