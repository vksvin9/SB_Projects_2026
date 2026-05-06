package com.vin.exception;

import com.vin.model.ApiResponse;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handle(Exception ex) {

        return ApiResponse.<String>builder()
                .status("ERROR")
                .message(ex.getMessage())
                .data(null)
                .timestamp(LocalDateTime.now())
                .traceId(UUID.randomUUID().toString())
                .build();
    }
}