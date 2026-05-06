package com.vin.util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil {

    public static String post(String urlStr, String payload) throws Exception {

        HttpURLConnection con = (HttpURLConnection) new URL(urlStr).openConnection();

        con.setConnectTimeout(3000); // detect service down fast
        con.setReadTimeout(3000);

        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setDoOutput(true);

        try (OutputStream os = con.getOutputStream()) {
            os.write(payload.getBytes());
        }

        int status = con.getResponseCode();

        InputStream is = (status >= 200 && status < 300)
                ? con.getInputStream()
                : con.getErrorStream();

        return "HTTP_" + status;
    }
}