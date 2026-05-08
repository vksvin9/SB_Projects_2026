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
public class StudentResultController {

    private final ResultRepository
            resultRepository;

    private final UserRepository
            userRepository;

    private final StudentRepository
            studentRepository;

    @GetMapping("/student/history")
    public String examHistory(
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

        model.addAttribute(
                "results",
                resultRepository.findByStudent(
                        student));

        return "student/history";
    }
}