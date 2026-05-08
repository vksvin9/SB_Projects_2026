package com.vin.dto.request;

import lombok.Data;

import java.util.Map;

@Data
public class ExamSubmissionRequest {

    private Long examId;

    /*
      questionId -> selectedOption
    */

    private Map<Long, String> answers;
}