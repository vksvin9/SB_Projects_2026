package com.vin.model;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AppDataRequest {

    @NotBlank
    private String reqId;

    @NotBlank
    private String name;

    @NotBlank
    private String type; // single | multi
}