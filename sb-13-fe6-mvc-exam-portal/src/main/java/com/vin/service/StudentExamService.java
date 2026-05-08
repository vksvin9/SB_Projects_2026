package com.vin.service;

import com.vin.dto.request.ExamSubmissionRequest;
import com.vin.dto.response.ExamResultResponse;
import com.vin.entity.Question;

import java.util.List;

public interface StudentExamService {

    List<Question> startExam(
            Long examId);

    ExamResultResponse submitExam(
            ExamSubmissionRequest request,
            String username);
}