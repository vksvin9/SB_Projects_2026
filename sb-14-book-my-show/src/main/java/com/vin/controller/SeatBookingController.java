package com.vin.controller;

import com.vin.dto.BookSeatRequest;
import com.vin.response.ApiResponse;
import com.vin.service.SeatBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/seats")
@RequiredArgsConstructor
public class SeatBookingController {

    private final SeatBookingService seatBookingService;

    @PostMapping("/book")
    public ApiResponse<String> bookSeat(
            @Valid @RequestBody BookSeatRequest request) {

        String response = seatBookingService
                .bookSeat(request.getSeatNumber());

        return ApiResponse.<String>builder()
                .success(true)
                .message("SUCCESS")
                .data(response)
                .build();
    }
}