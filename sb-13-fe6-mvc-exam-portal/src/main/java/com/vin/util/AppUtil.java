package com.vin.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppUtil {

    private AppUtil() {
    }

    public static String formatDate(LocalDateTime time) {

        return time.format(
                DateTimeFormatter.ofPattern(
                        "dd-MM-yyyy HH:mm:ss"));
    }
}