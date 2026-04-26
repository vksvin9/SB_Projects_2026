package com.vin.dto;

public class ApiResponse<T> {

    private boolean success;
    private String message;
    private T data;
    private Object errors;

    private ApiResponse(boolean success, String message, T data, Object errors) {
        this.success = success;
        this.message = message;
        this.data = data;
        this.errors = errors;
    }

    public static <T> ApiResponse<T> success(String msg, T data) {
        return new ApiResponse<>(true, msg, data, null);
    }

    public static <T> ApiResponse<T> failure(String msg) {
        return new ApiResponse<>(false, msg, null, null);
    }

    public static <T> ApiResponse<T> validation(String msg, Object errors) {
        return new ApiResponse<>(false, msg, null, errors);
    }

	public boolean isSuccess() {
		return success;
	}

	// getters
	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public Object getErrors() {
		return errors;
	}

}