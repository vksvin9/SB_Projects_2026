package com.vin.dto.response;

import lombok.Data;

@Data
public class ExamResultResponse {

    private String examTitle;

    private Integer totalQuestions;

    private Integer correctAnswers;

    private Integer wrongAnswers;

    private Integer score;

    private Double percentage;

    private String status;
}