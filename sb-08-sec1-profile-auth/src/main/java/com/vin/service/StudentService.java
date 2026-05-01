package com.vin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vin.entity.Student;
import com.vin.repository.StudentRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repo;

    // CREATE
    public Student create(Student student) {

        long start = System.currentTimeMillis();
        log.info("SERVICE START -> createStudent | name={}", student.getName());

        log.info("DB CALL -> save student");
        Student saved = repo.save(student);

        log.info("SERVICE END -> createStudent | id={} | time={}ms",
                saved.getId(),
                System.currentTimeMillis() - start);

        return saved;
    }

    // READ ALL
    public List<Student> getAll() {

        long start = System.currentTimeMillis();
        log.info("SERVICE START -> getAllStudents");

        log.info("DB CALL -> fetch students");
        List<Student> list = repo.findAll();

        log.info("SERVICE END -> getAllStudents | count={} | time={}ms",
                list.size(),
                System.currentTimeMillis() - start);

        return list;
    }

    // READ BY ID
    public Student getById(Long id) {

        long start = System.currentTimeMillis();
        log.info("SERVICE START -> getStudentById | id={}", id);

        log.info("DB CALL -> findById");
        Student s = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        log.info("SERVICE END -> getStudentById | time={}ms",
                System.currentTimeMillis() - start);

        return s;
    }

    // UPDATE
    public Student update(Long id, Student req) {

        long start = System.currentTimeMillis();
        log.info("SERVICE START -> updateStudent | id={}", id);

        Student existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(req.getName());
        existing.setAge(req.getAge());
        existing.setEmail(req.getEmail());

        log.info("DB CALL -> update student");
        Student updated = repo.save(existing);

        log.info("SERVICE END -> updateStudent | time={}ms",
                System.currentTimeMillis() - start);

        return updated;
    }

    // DELETE
    public void delete(Long id) {

        long start = System.currentTimeMillis();
        log.info("SERVICE START -> deleteStudent | id={}", id);

        log.info("DB CALL -> deleteById");
        repo.deleteById(id);

        log.info("SERVICE END -> deleteStudent | time={}ms",
                System.currentTimeMillis() - start);
    }
}