package com.vin.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuestionRequest {

    @NotBlank(message = "Question required")
    private String questionText;

    @NotBlank(message = "Option A required")
    private String optionA;

    @NotBlank(message = "Option B required")
    private String optionB;

    @NotBlank(message = "Option C required")
    private String optionC;

    @NotBlank(message = "Option D required")
    private String optionD;

    @NotBlank(message = "Correct answer required")
    private String correctAnswer;

    @NotNull(message = "Marks required")
    private Integer marks;

    @NotNull(message = "Exam required")
    private Long examId;
}