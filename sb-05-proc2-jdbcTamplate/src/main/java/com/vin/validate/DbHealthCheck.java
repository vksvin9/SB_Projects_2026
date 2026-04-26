package com.vin.validate;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;

@Component
public class DbHealthCheck {

    private final JdbcTemplate jdbcTemplate;

    public DbHealthCheck(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @PostConstruct
    public void check() {
        Integer result = jdbcTemplate.queryForObject("SELECT 1 FROM dual", Integer.class);
        System.out.println("DB Connected: " + result);
    }
}