package com.vin.exception;

import java.sql.SQLException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vin.common.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<?>> handleRuntime(RuntimeException ex) {

        SQLException sqlEx = extractSqlException(ex);

        if (sqlEx != null) {
            int code = sqlEx.getErrorCode();

            if (code == 20001 || code == 20002 || code == 20003) {
                return build(HttpStatus.NOT_FOUND, "User not found", code);
            }

            return build(HttpStatus.INTERNAL_SERVER_ERROR, "Database error", code);
        }

        return build(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), null);
    }

    private SQLException extractSqlException(Throwable ex) {
        while (ex != null) {
            if (ex instanceof SQLException) return (SQLException) ex;
            ex = ex.getCause();
        }
        return null;
    }

    private ResponseEntity<ApiResponse<?>> build(HttpStatus status,
                                                 String msg,
                                                 Integer code) {

        String message = (code != null)
                ? msg + " (ORA-" + code + ")"
                : msg;

        return new ResponseEntity<>(
                ApiResponse.error(status.value(), message),
                status
        );
    }
}