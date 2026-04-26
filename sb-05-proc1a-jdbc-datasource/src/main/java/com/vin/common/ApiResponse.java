package com.vin.common;

import java.time.LocalDateTime;

public class ApiResponse<T> {

    private LocalDateTime timestamp;
    private int status;
    private String message;
    private T data;

    public ApiResponse(int status, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(200, "success", data);
    }

    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(200, msg, data);
    }

    public static <T> ApiResponse<T> error(int status, String msg) {
        return new ApiResponse<>(status, msg, null);
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public int getStatus() { return status; }
    public String getMessage() { return message; }
    public T getData() { return data; }
}