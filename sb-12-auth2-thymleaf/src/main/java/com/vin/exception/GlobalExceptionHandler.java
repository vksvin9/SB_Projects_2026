package com.vin.exception;

import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.vin.common.ApiResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    // =========================
    // IGNORE STATIC RESOURCE ERRORS
    // =========================
    @ExceptionHandler(org.springframework.web.servlet.resource.NoResourceFoundException.class)
    public void handleNoResource() {
        // do nothing (prevents noise logs)
    }

    // =========================
    // HANDLE DB EXCEPTIONS
    // =========================
    @ExceptionHandler({DataAccessException.class, UncategorizedSQLException.class})
    public ApiResponse<?> handleDataAccess(Exception ex) {

        log.error("Database Exception occurred", ex);

        SQLException sqlEx = extractSQLException(ex);

        if (sqlEx != null) {
            return mapOracleError(sqlEx);
        }

        return build("ERROR", "Database error");
    }

    // =========================
    // HANDLE SQL DIRECTLY
    // =========================
    @ExceptionHandler(SQLException.class)
    public ApiResponse<?> handleSQLException(SQLException ex) {
        return mapOracleError(ex);
    }

    // =========================
    // GENERIC
    // =========================
    @ExceptionHandler(Exception.class)
    public ApiResponse<?> handleGeneric(Exception ex) {

        log.error("Unhandled exception", ex);

        return build("ERROR", "Internal server error");
    }

    // =========================
    // HELPERS
    // =========================
    private SQLException extractSQLException(Throwable ex) {
        while (ex != null) {
            if (ex instanceof SQLException) return (SQLException) ex;
            ex = ex.getCause();
        }
        return null;
    }

    private ApiResponse<?> mapOracleError(SQLException ex) {

        int code = ex.getErrorCode();

        return switch (code) {
            case 1 -> build("ERROR", "Duplicate UUID - already processed");
            case 20003 -> build("ERROR", "Invalid username or password");
            case 20011 -> build("ERROR", "Invalid token");
            case 20012 -> build("ERROR", "Invalid service");
            case 20013 -> build("ERROR", "Not authorised to use this service_api");
            case 20002 -> build("ERROR", "Rate limit exceeded");
            default -> build("ERROR", "Database error");
        };
    }

    private ApiResponse<?> build(String status, String message) {
        return ApiResponse.builder()
                .status(status)
                .message(message)
                .data(null)
                .build();
    }
}