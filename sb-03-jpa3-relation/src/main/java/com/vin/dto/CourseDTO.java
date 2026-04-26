package com.vin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CourseDTO {

    private Long id;

    @NotBlank
    private String name;
}
