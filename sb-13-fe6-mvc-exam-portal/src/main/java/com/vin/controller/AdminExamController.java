package com.vin.controller;

import com.vin.dto.request.ExamRequest;
import com.vin.service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/exams")
@RequiredArgsConstructor
public class AdminExamController {

    private final ExamService examService;

    @GetMapping
    public String examList(Model model) {

        model.addAttribute(
                "exams",
                examService.getAllExams()
        );

        return "admin/exams";
    }

    @GetMapping("/create")
    public String createExamForm(Model model) {

        model.addAttribute(
                "examRequest",
                new ExamRequest()
        );

        model.addAttribute(
                "pageTitle",
                "Create Exam"
        );

        return "admin/exam-form";
    }

    @PostMapping("/save")
    public String saveExam(
            @Valid
            @ModelAttribute ExamRequest examRequest,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "pageTitle",
                    "Create Exam"
            );

            return "admin/exam-form";
        }

        examService.createExam(examRequest);

        return "redirect:/admin/exams";
    }

    @GetMapping("/edit/{id}")
    public String editExamForm(
            @PathVariable Long id,
            Model model) {

        var exam =
                examService.getExamById(id);

        ExamRequest request =
                new ExamRequest();

        request.setTitle(exam.getTitle());
        request.setDescription(
                exam.getDescription());
        request.setDurationMinutes(
                exam.getDurationMinutes());
        request.setTotalMarks(
                exam.getTotalMarks());

        model.addAttribute(
                "examRequest",
                request
        );

        model.addAttribute(
                "examId",
                id
        );

        model.addAttribute(
                "pageTitle",
                "Edit Exam"
        );

        return "admin/exam-form";
    }

    @PostMapping("/update/{id}")
    public String updateExam(
            @PathVariable Long id,
            @Valid
            @ModelAttribute ExamRequest examRequest,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "pageTitle",
                    "Edit Exam"
            );

            return "admin/exam-form";
        }

        examService.updateExam(
                id,
                examRequest
        );

        return "redirect:/admin/exams";
    }

    @GetMapping("/delete/{id}")
    public String deleteExam(
            @PathVariable Long id) {

        examService.deleteExam(id);

        return "redirect:/admin/exams";
    }
}