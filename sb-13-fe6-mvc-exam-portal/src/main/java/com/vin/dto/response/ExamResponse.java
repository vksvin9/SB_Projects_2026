package com.vin.dto.response;

import lombok.Data;

@Data
public class ExamResponse {

    private Long id;

    private String title;

    private String description;

    private Integer durationMinutes;

    private Integer totalMarks;
}