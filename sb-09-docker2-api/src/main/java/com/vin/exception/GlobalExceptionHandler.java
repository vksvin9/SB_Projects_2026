package com.vin.exception;

import com.vin.common.ApiResponse;
import org.slf4j.*;
import org.springframework.web.bind.annotation.*;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handle(Exception ex) {
        log.error("Unhandled exception", ex);
        return ApiResponse.fail(ex.getMessage());
    }
}