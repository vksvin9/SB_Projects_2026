package com.vin.dao;

import org.springframework.jdbc.core.ConnectionCallback;

public interface ProcedureExecutor {
    <T> T execute(ConnectionCallback<T> callback);
}