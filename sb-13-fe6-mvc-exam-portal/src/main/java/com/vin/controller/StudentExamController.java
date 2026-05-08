package com.vin.controller;

import com.vin.dto.request.ExamSubmissionRequest;
import com.vin.service.ExamService;
import com.vin.service.StudentExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student/exams")
@RequiredArgsConstructor
public class StudentExamController {

        private final ExamService examService;

        private final StudentExamService studentExamService;

        @GetMapping
        public String availableExams(
                        Model model) {

                model.addAttribute(
                                "exams",
                                examService.getAllExams());

                return "student/exams";
        }

        @GetMapping("/start/{examId}")
        public String startExam(
                        @PathVariable Long examId,
                        Model model) {

                var questions = studentExamService.startExam(
                                examId);

                var exam = examService.getExamById(
                                examId);

                model.addAttribute(
                                "questions",
                                questions);

                model.addAttribute(
                                "examId",
                                examId);

                model.addAttribute(
                                "durationMinutes",
                                exam.getDurationMinutes());

                return "student/take-exam";
        }

        @PostMapping("/submit")
        public String submitExam(
                        @ModelAttribute ExamSubmissionRequest request,

                        Authentication authentication,

                        Model model) {

                var result = studentExamService.submitExam(
                                request,
                                authentication.getName());

                model.addAttribute(
                                "result",
                                result);

                return "student/exam-result";
        }
}