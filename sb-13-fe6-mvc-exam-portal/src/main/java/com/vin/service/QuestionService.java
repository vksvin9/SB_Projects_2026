package com.vin.service;

import com.vin.dto.request.QuestionRequest;
import com.vin.dto.response.QuestionResponse;

import java.util.List;

public interface QuestionService {

    QuestionResponse createQuestion(
            QuestionRequest request);

    List<QuestionResponse>
    getQuestionsByExam(Long examId);

    QuestionResponse getQuestionById(
            Long id);

    QuestionResponse updateQuestion(
            Long id,
            QuestionRequest request);

    void deleteQuestion(Long id);
}