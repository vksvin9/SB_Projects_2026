package com.vin.controller;

import com.vin.dto.ApiResponse;
import com.vin.dto.SampleRequest;
import com.vin.service.SampleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/sample")
@RequiredArgsConstructor
public class SampleController {

    private final SampleService sampleService;

    @GetMapping("/hello")
    public ApiResponse<String> hello(
            @RequestParam(defaultValue = "Vin") String name) {

        return ApiResponse.<String>builder()
                .success(true)
                .message("Request successful")
                .data(sampleService.greet(name))
                .timestamp(LocalDateTime.now())
                .build();
    }

    @PostMapping("/validate")
    public ApiResponse<String> validate(
            @Valid @RequestBody SampleRequest request) {

        return ApiResponse.<String>builder()
                .success(true)
                .message("Validation successful")
                .data("Received name: " + request.getName())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @GetMapping("/{id}")
    public ApiResponse<String> findById(@PathVariable Long id) {

        return ApiResponse.<String>builder()
                .success(true)
                .message("Request successful")
                .data(sampleService.findById(id))
                .timestamp(LocalDateTime.now())
                .build();
    }
}