package com.vin.controller;

import com.vin.model.*;
import com.vin.service.AppService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pull")
@RequiredArgsConstructor
public class AppController {

    private final AppService service;

    // Single
    @PostMapping("/single")
    public ApiResponse<String> single(@Valid @RequestBody AppDataRequest req) {
        service.processSingle(req);
        return ApiResponse.<String>builder().status("SUCCESS").message("Inserted").build();
    }

    // Bulk
    @PostMapping("/bulk")
    public ApiResponse<String> bulk(@Valid @RequestBody List<@Valid AppDataRequest> list) {
        service.processBulk(list);
        return ApiResponse.<String>builder().status("SUCCESS").message("Bulk Inserted").build();
    }

    // Fetch
    @GetMapping("/records")
    public ApiResponse<List<AppDataResponse>> fetch(
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(defaultValue = "0") int offset) {

        return ApiResponse.<List<AppDataResponse>>builder()
                .status("SUCCESS")
                .data(service.fetch(limit, offset))
                .build();
    }
}