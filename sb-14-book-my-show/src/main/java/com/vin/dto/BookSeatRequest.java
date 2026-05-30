package com.vin.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class BookSeatRequest {

    @NotBlank
    private String seatNumber;
}