package com.vin.dao;

import java.sql.CallableStatement;
import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Repository
public class AppRepository {

    private final DataSource ds;

    public AppRepository(@Qualifier("appDataSource") DataSource ds) {
        this.ds = ds;
    }

    public void insertLog(String msg) {
        try (Connection con = ds.getConnection();
             CallableStatement cs =
                     con.prepareCall("{call app_user.insert_log_proc(?)}")) {

            cs.setString(1, msg);
            cs.execute();

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }
}