package com.vin.config;

import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {

    private static final Properties properties = new Properties();

    static {
        try (InputStream input =
                PropertiesLoader.class.getClassLoader()
                        .getResourceAsStream("application.properties")) {

            properties.load(input);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load properties", e);
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }
}