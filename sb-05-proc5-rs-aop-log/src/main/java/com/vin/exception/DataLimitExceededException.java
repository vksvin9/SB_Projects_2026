package com.vin.exception;

public class DataLimitExceededException extends RuntimeException {
    public DataLimitExceededException(String msg) {
        super(msg);
    }
}