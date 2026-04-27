package com.vin.exception;

import com.vin.response.ApiResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<?>> handleDb(DataAccessException ex) {

        return ResponseEntity.badRequest().body(
                ApiResponse.failure("DB Error", ex.getMostSpecificCause().getMessage())
        );
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntime(RuntimeException ex) {

        return ResponseEntity.badRequest().body(
                ApiResponse.failure("Error", ex.getMessage())
        );
    }
}