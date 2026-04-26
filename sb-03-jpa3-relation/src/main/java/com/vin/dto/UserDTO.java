package com.vin.dto;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDTO {

    private Long id;

    @NotBlank
    private String name;

    private ProfileDTO profile;

    private List<AddressDTO> addresses;

    private List<CourseDTO> courses;
}