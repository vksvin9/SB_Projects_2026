package com.vin.util;

import java.sql.Connection;
import java.sql.Statement;

public class DBInitializer {

    public static void init() {
        try {
            Connection conn = DBConnection.getInstance();
            Statement st = conn.createStatement();

            st.execute("""
                CREATE TABLE users (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100),
                    email VARCHAR(100)
                )
            """);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}