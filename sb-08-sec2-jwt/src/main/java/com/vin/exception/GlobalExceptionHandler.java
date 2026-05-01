package com.vin.exception;

import com.vin.common.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handle(Exception ex) {
        return ResponseEntity.internalServerError()
                .body(ApiResponse.builder()
                        .success(false)
                        .error(ex.getMessage())
                        .build());
    }
}