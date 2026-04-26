package com.vin.exception;

import com.vin.common.ApiResponse;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // HANDLE DB / ORACLE EXCEPTIONS
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<ApiResponse<?>> handleDataAccess(DataAccessException ex) {

        SQLException sqlEx = extractSqlException(ex);

        if (sqlEx != null) {
            int code = sqlEx.getErrorCode();

            switch (code) {
                case 20001: // update_user_proc
                case 20002: // get_user_no_cursor_1
                case 20003: // get_user_no_cursor_2
                    return build(HttpStatus.NOT_FOUND, "User not found", code);

                default:
                    return build(HttpStatus.INTERNAL_SERVER_ERROR,
                            "Database error",
                            code);
            }
        }

        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                "Unexpected database error",
                null);
    }

    // HANDLE GENERIC EXCEPTIONS
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<?>> handleGeneric(Exception ex) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                null);
    }

    // -------- HELPER METHODS --------

    private SQLException extractSqlException(Throwable ex) {
        while (ex != null) {
            if (ex instanceof SQLException) {
                return (SQLException) ex;
            }
            ex = ex.getCause();
        }
        return null;
    }

    private ResponseEntity<ApiResponse<?>> build(HttpStatus status,
                                                String message,
                                                Integer code) {

        String finalMessage = message;

        if (code != null) {
            finalMessage = message + " (ORA-" + code + ")";
        }

        return new ResponseEntity<>(
                ApiResponse.error(status.value(), finalMessage),
                status
        );
    }
}