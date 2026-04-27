package com.vin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vin.exception.ApiException;
import com.vin.model.Post;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@Service
public class HttpUrlConnectionClient {

    @Value("${api.base-url}")
    private String baseUrl;

    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Post> getPosts() {

        try {
            URL url = new URL(baseUrl + "/posts");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();

            InputStream stream =
                    (status >= 200 && status < 300)
                            ? conn.getInputStream()
                            : conn.getErrorStream();

            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(stream));

            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();

            if (status < 200 || status >= 300) {
                throw new ApiException("External API error: " + status);
            }

            Post[] posts = objectMapper.readValue(response.toString(), Post[].class);

            return Arrays.asList(posts);

        } catch (Exception e) {
            throw new ApiException("Failed to call external API", e);
        }
    }
}