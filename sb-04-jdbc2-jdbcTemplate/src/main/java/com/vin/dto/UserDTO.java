package com.vin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank
    private String name;

    @Email
    private String email;

    @Min(18)
    private Integer age;
}