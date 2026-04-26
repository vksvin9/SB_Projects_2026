package com.vin.dao;

import org.springframework.jdbc.core.ConnectionCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ProcedureExecutorImpl implements ProcedureExecutor {

    private final JdbcTemplate jdbcTemplate;

    public ProcedureExecutorImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public <T> T execute(ConnectionCallback<T> callback) {
        return jdbcTemplate.execute(callback);
    }
}