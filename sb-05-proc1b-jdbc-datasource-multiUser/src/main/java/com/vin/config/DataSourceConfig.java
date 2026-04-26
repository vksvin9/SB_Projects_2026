package com.vin.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "userDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.userdb")
    public DataSource userDataSource() {
        return new HikariDataSource();
    }

    @Bean(name = "appDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.appdb")
    public DataSource appDataSource() {
        return new HikariDataSource();
    }
}