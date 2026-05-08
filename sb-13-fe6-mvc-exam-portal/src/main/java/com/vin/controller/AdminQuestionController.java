package com.vin.controller;

import com.vin.dto.request.QuestionRequest;
import com.vin.service.ExamService;
import com.vin.service.QuestionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/questions")
@RequiredArgsConstructor
public class AdminQuestionController {

    private final QuestionService
            questionService;

    private final ExamService
            examService;

    @GetMapping("/{examId}")
    public String questionList(
            @PathVariable Long examId,
            Model model) {

        model.addAttribute(
                "questions",
                questionService.getQuestionsByExam(
                        examId));

        model.addAttribute(
                "examId",
                examId);

        return "admin/questions";
    }

    @GetMapping("/create/{examId}")
    public String createQuestionForm(
            @PathVariable Long examId,
            Model model) {

        QuestionRequest request =
                new QuestionRequest();

        request.setExamId(examId);

        model.addAttribute(
                "questionRequest",
                request);

        model.addAttribute(
                "exams",
                examService.getAllExams());

        model.addAttribute(
                "pageTitle",
                "Add Question");

        return "admin/question-form";
    }

    @PostMapping("/save")
    public String saveQuestion(
            @Valid
            @ModelAttribute QuestionRequest request,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "exams",
                    examService.getAllExams());

            model.addAttribute(
                    "pageTitle",
                    "Add Question");

            return "admin/question-form";
        }

        questionService.createQuestion(
                request);

        return "redirect:/admin/questions/"
                + request.getExamId();
    }

    @GetMapping("/edit/{id}")
    public String editQuestionForm(
            @PathVariable Long id,
            Model model) {

        var question =
                questionService.getQuestionById(id);

        QuestionRequest request =
                new QuestionRequest();

        request.setQuestionText(
                question.getQuestionText());

        request.setOptionA(
                question.getOptionA());

        request.setOptionB(
                question.getOptionB());

        request.setOptionC(
                question.getOptionC());

        request.setOptionD(
                question.getOptionD());

        request.setCorrectAnswer(
                question.getCorrectAnswer());

        request.setMarks(
                question.getMarks());

        model.addAttribute(
                "questionId",
                id);

        model.addAttribute(
                "questionRequest",
                request);

        model.addAttribute(
                "exams",
                examService.getAllExams());

        model.addAttribute(
                "pageTitle",
                "Edit Question");

        return "admin/question-form";
    }

    @PostMapping("/update/{id}")
    public String updateQuestion(
            @PathVariable Long id,
            @Valid
            @ModelAttribute QuestionRequest request,
            BindingResult bindingResult,
            Model model) {

        if (bindingResult.hasErrors()) {

            model.addAttribute(
                    "exams",
                    examService.getAllExams());

            model.addAttribute(
                    "pageTitle",
                    "Edit Question");

            return "admin/question-form";
        }

        questionService.updateQuestion(
                id,
                request);

        return "redirect:/admin/questions/"
                + request.getExamId();
    }

    @GetMapping("/delete/{id}/{examId}")
    public String deleteQuestion(
            @PathVariable Long id,
            @PathVariable Long examId) {

        questionService.deleteQuestion(id);

        return "redirect:/admin/questions/"
                + examId;
    }
}