package com.vin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.vin.model.ApiResponse;
import com.vin.service.EtlService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/etl")
@RequiredArgsConstructor
public class EtlController {

    private final EtlService etlService;

    // =========================
    // ETL Upload
    // =========================
    @PostMapping("/upload")
    public ApiResponse<String> upload(@RequestParam("file") MultipartFile file) {

        etlService.process(file);

        return ApiResponse.<String>builder()
                .status("SUCCESS")
                .message("File processed")
                .build();
    }

    // =========================
    // VERIFY ETL (FIXED)
    // =========================
    @GetMapping("/latest")
    public ApiResponse<List<Map<String, Object>>> latest(
            @RequestParam(defaultValue = "10") int limit) {

        return ApiResponse.<List<Map<String, Object>>>builder()
                .status("SUCCESS")
                .message("Latest records")
                .data(etlService.getLatest(limit))
                .build();
    }
}