package com.vin.exception;

import com.vin.dto.CommonResponse;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public CommonResponse<String> handle(Exception ex) {
        return CommonResponse.<String>builder()
                .status("ERROR")
                .message(ex.getMessage())
                .data(null)
                .build();
    }
}