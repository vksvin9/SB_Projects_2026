package com.vin.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppDataRequest {

    @NotBlank(message = "reqId is mandatory")
    private String reqId;

    @NotBlank(message = "name is mandatory")
    private String name;

    @NotBlank(message = "type is mandatory")
    @Pattern(regexp = "single|multi", message = "type must be 'single' or 'multi'")
    private String type;
}