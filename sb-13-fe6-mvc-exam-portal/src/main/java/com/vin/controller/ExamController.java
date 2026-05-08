package com.vin.controller;

import com.vin.dto.request.ExamRequest;
import com.vin.dto.response.ApiResponse;
import com.vin.dto.response.ExamResponse;
import com.vin.service.ExamService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/exams")
@RequiredArgsConstructor
public class ExamController {

    private final ExamService examService;

    @PostMapping
    public ApiResponse<ExamResponse> createExam(
            @Valid
            @RequestBody ExamRequest request) {

        return ApiResponse.<ExamResponse>builder()
                .success(true)
                .message("Exam Created")
                .data(
                        examService.createExam(request)
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping
    public ApiResponse<List<ExamResponse>>
    getAllExams() {

        return ApiResponse.<List<ExamResponse>>
                builder()
                .success(true)
                .message("Exam List")
                .data(
                        examService.getAllExams()
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<ExamResponse>
    getExamById(
            @PathVariable Long id) {

        return ApiResponse.<ExamResponse>
                builder()
                .success(true)
                .message("Exam Details")
                .data(
                        examService.getExamById(id)
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PutMapping("/{id}")
    public ApiResponse<ExamResponse>
    updateExam(
            @PathVariable Long id,
            @Valid
            @RequestBody ExamRequest request) {

        return ApiResponse.<ExamResponse>
                builder()
                .success(true)
                .message("Exam Updated")
                .data(
                        examService.updateExam(
                                id,
                                request
                        )
                )
                .timestamp(LocalDateTime.now())
                .build();
    }

    @DeleteMapping("/{id}")
    public ApiResponse<String>
    deleteExam(
            @PathVariable Long id) {

        examService.deleteExam(id);

        return ApiResponse.<String>
                builder()
                .success(true)
                .message("Exam Deleted")
                .data("Deleted Successfully")
                .timestamp(LocalDateTime.now())
                .build();
    }
}