package com.vin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SampleRequest {

    @NotBlank(message = "Name is required")
    private String name;
}