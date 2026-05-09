package com.vin.exception;

import com.vin.dto.ChatResponseDto;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ChatResponseDto handleException(Exception ex) {
        return new ChatResponseDto(
                "Unable to process your request. Please ensure Ollama is running."
        );
    }
}