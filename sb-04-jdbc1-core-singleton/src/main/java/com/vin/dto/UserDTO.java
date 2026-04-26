package com.vin.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    private int id;

    @NotBlank(message = "Name is required")
    private String name;

    @Email(message = "Invalid email")
    private String email;
}