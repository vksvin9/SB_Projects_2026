package com.vin.controller;

import com.vin.model.ApiResponse;
import com.vin.service.PushService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/push")
@RequiredArgsConstructor
public class PushController {

    private final PushService service;

    // =========================
    // PUSH SINGLE
    // =========================
    @PostMapping("/single")
    public ApiResponse<String> single() {

        service.pushSingle();

        return ApiResponse.<String>builder()
                .status("SUCCESS")
                .message("Single push executed")
                .data(null)
                .timestamp(LocalDateTime.now())
                .traceId(UUID.randomUUID().toString())
                .build();
    }

    // =========================
    // PUSH BATCH
    // =========================
    @PostMapping("/batch")
    public ApiResponse<String> batch() {

        service.pushBatch();

        return ApiResponse.<String>builder()
                .status("SUCCESS")
                .message("Batch push executed")
                .data(null)
                .timestamp(LocalDateTime.now())
                .traceId(UUID.randomUUID().toString())
                .build();
    }

    // =========================
    // ETL
    // =========================
    @PostMapping("/etl")
    public ApiResponse<String> etl(@RequestParam MultipartFile file) throws Exception {

        service.etl(file);

        return ApiResponse.<String>builder()
                .status("SUCCESS")
                .message("ETL completed")
                .data(null)
                .timestamp(LocalDateTime.now())
                .traceId(UUID.randomUUID().toString())
                .build();
    }
}