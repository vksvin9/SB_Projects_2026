package com.vin.exception;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vin.dto.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiResponse<?>> handleValidation(MethodArgumentNotValidException ex) {

		String errors = ex.getBindingResult().getFieldErrors().stream()
				.map(e -> e.getField() + ":" + e.getDefaultMessage()).collect(Collectors.joining(", "));

		return ResponseEntity.badRequest().body(ApiResponse.validation("Validation Failed", errors));
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ApiResponse<?>> handleDuplicate() {
		return ResponseEntity.badRequest().body(ApiResponse.failure("Email already exists"));
	}

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse<?>> handleNotFound(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponse.failure(ex.getMessage()));
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiResponse<?>> handleGeneric(Exception ex) {
		log.error("Unhandled Exception: ", ex); // 🔥 THIS WILL ALWAYS PRINT
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(ApiResponse.failure("Something went wrong"));
	}
}