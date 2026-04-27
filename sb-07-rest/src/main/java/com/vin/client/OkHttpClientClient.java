package com.vin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vin.exception.ApiException;
import com.vin.model.Post;
import lombok.RequiredArgsConstructor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OkHttpClientClient  {

    private final OkHttpClient client;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${api.base-url}")
    private String baseUrl;

    public List<Post> getPosts() {

        Request request = new Request.Builder()
                .url(baseUrl + "/posts")
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {

            if (!response.isSuccessful()) {
                throw new ApiException("OkHttp error: " + response.code());
            }

            return Arrays.asList(
                    mapper.readValue(response.body().string(), Post[].class)
            );

        } catch (Exception e) {
            throw new ApiException("OkHttp failed", e);
        }
    }
}