package com.vin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AddressDTO {

    private Long id;

    @NotBlank
    private String city;

    @NotBlank
    private String state;
}