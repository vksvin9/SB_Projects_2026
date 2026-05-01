package com.vin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.common.ApiResponse;
import com.vin.entity.Student;
import com.vin.service.StudentService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService service;

    // CREATE
    @PostMapping
    public ApiResponse<Student> create(@RequestBody Student student) {

        long start = System.currentTimeMillis();
        log.info("API IN -> POST /students | name={}", student.getName());

        Student saved = service.create(student);

        log.info("API OUT -> POST /students | time={}ms",
                System.currentTimeMillis() - start);

        return ApiResponse.<Student>builder()
                .success(true)
                .data(saved)
                .build();
    }

    // READ ALL
    @GetMapping
    public ApiResponse<List<Student>> getAll() {

        long start = System.currentTimeMillis();
        log.info("API IN -> GET /students");

        List<Student> list = service.getAll();

        log.info("API OUT -> GET /students | count={} | time={}ms",
                list.size(),
                System.currentTimeMillis() - start);

        return ApiResponse.<List<Student>>builder()
                .success(true)
                .data(list)
                .build();
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ApiResponse<Student> getById(@PathVariable Long id) {

        long start = System.currentTimeMillis();
        log.info("API IN -> GET /students/{}", id);

        Student s = service.getById(id);

        log.info("API OUT -> GET /students/{} | time={}ms",
                id,
                System.currentTimeMillis() - start);

        return ApiResponse.<Student>builder()
                .success(true)
                .data(s)
                .build();
    }

    // UPDATE
    @PutMapping("/{id}")
    public ApiResponse<Student> update(@PathVariable Long id,
                                       @RequestBody Student student) {

        long start = System.currentTimeMillis();
        log.info("API IN -> PUT /students/{}", id);

        Student updated = service.update(id, student);

        log.info("API OUT -> PUT /students/{} | time={}ms",
                id,
                System.currentTimeMillis() - start);

        return ApiResponse.<Student>builder()
                .success(true)
                .data(updated)
                .build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ApiResponse<?> delete(@PathVariable Long id) {

        long start = System.currentTimeMillis();
        log.info("API IN -> DELETE /students/{}", id);

        service.delete(id);

        log.info("API OUT -> DELETE /students/{} | time={}ms",
                id,
                System.currentTimeMillis() - start);

        return ApiResponse.builder()
                .success(true)
                .message("Deleted")
                .build();
    }
}