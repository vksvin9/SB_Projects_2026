package com.vin.service;

import com.vin.dto.request.ExamRequest;
import com.vin.dto.response.ExamResponse;

import java.util.List;

public interface ExamService {

    ExamResponse createExam(
            ExamRequest request);

    List<ExamResponse> getAllExams();

    ExamResponse getExamById(Long id);

    ExamResponse updateExam(
            Long id,
            ExamRequest request);

    void deleteExam(Long id);
}