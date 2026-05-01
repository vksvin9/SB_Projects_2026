package com.vin.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vin.dto.CommonResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataLimitExceededException.class)
    public CommonResponse<?> handleLimit(DataLimitExceededException ex) {
        return CommonResponse.builder()
                .status("FAIL")
                .message(ex.getMessage())
                .build();
    }

    @ExceptionHandler(Exception.class)
    public CommonResponse<?> handle(Exception ex) {
        return CommonResponse.builder()
                .status("ERROR")
                .message(ex.getMessage())
                .build();
    }
}