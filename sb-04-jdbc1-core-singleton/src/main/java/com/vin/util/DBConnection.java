package com.vin.util;

import java.sql.Connection;
import java.sql.DriverManager;

import com.vin.config.PropertiesLoader;

//DBConnection (Singleton)
public class DBConnection {

    private static Connection connection;

    private DBConnection() {}

    public static Connection getInstance() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        PropertiesLoader.get("db.url"),
                        PropertiesLoader.get("db.username"),
                        PropertiesLoader.get("db.password"));

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return connection;
    }
}
