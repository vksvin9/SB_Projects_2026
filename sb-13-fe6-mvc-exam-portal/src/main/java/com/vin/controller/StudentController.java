package com.vin.controller;

import com.vin.entity.Student;
import com.vin.entity.User;
import com.vin.repository.ResultRepository;
import com.vin.repository.StudentRepository;
import com.vin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class StudentController {

    private final UserRepository
            userRepository;

    private final StudentRepository
            studentRepository;

    private final ResultRepository
            resultRepository;

    @GetMapping("/student/dashboard")
    public String dashboard(
            Authentication authentication,
            Model model) {

        User user =
                userRepository.findByUsername(
                        authentication.getName())
                        .orElseThrow();

        Student student =
                studentRepository.findAll()
                        .stream()
                        .filter(s ->
                                s.getUser()
                                        .getId()
                                        .equals(user.getId()))
                        .findFirst()
                        .orElseThrow();

        var results =
                resultRepository.findByStudent(
                        student);

        long passed =
                results.stream()
                        .filter(r ->
                                r.getStatus()
                                        .equals("PASS"))
                        .count();

        long failed =
                results.stream()
                        .filter(r ->
                                r.getStatus()
                                        .equals("FAIL"))
                        .count();

        model.addAttribute(
                "attempted",
                results.size());

        model.addAttribute(
                "passed",
                passed);

        model.addAttribute(
                "failed",
                failed);

        return "student/dashboard";
    }
}