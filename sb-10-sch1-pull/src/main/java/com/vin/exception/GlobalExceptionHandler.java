package com.vin.exception;

import com.vin.model.ApiResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResponse<String> validation(MethodArgumentNotValidException ex) {
        String msg = ex.getBindingResult().getFieldError().getDefaultMessage();
        return ApiResponse.<String>builder().status("FAIL").message(msg).build();
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handle(Exception ex) {
        return ApiResponse.<String>builder().status("ERROR").message(ex.getMessage()).build();
    }
}