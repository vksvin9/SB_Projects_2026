package com.vin.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.dto.CourseDTO;
import com.vin.entity.Course;
import com.vin.service.CourseService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

    private final CourseService courseService;

    @PostMapping
    public ResponseEntity<Course> create(@RequestBody @Valid CourseDTO dto) {
        return ResponseEntity.ok(courseService.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<Course>> getAll() {
        return ResponseEntity.ok(courseService.getAll());
    }
}
