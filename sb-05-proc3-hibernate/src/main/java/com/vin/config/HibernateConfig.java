package com.vin.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.*;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
public class HibernateConfig {

    @Bean
    public SessionFactory sessionFactory(DataSource dataSource) {

        org.hibernate.cfg.Configuration cfg = new org.hibernate.cfg.Configuration();

        Properties props = new Properties();
        props.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");
        props.put("hibernate.show_sql", true);

        cfg.setProperties(props);

        var serviceRegistry = new org.hibernate.boot.registry.StandardServiceRegistryBuilder()
                .applySettings(cfg.getProperties())
                .applySetting("hibernate.connection.datasource", dataSource)
                .build();

        return cfg.buildSessionFactory(serviceRegistry);
    }

    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sf) {
        return new HibernateTransactionManager(sf);
    }
}