package com.vin.client;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vin.exception.ApiException;
import com.vin.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.*;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class JavaHttpClientClient {

    @Value("${api.base-url}")
    private String baseUrl;

    private final HttpClient client = HttpClient.newHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    // ===== SYNC =====
    public List<Post> getPosts() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(baseUrl + "/posts"))
                    .GET()
                    .build();

            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() >= 300) {
                throw new ApiException("Java HttpClient error: " + response.statusCode());
            }

            return Arrays.asList(mapper.readValue(response.body(), Post[].class));

        } catch (Exception e) {
            throw new ApiException("Java HttpClient failed", e);
        }
    }

    // ===== ASYNC =====
    public CompletableFuture<List<Post>> getPostsAsync() {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(baseUrl + "/posts"))
                .GET()
                .build();

        return client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(response -> {
                    try {
                        if (response.statusCode() >= 300) {
                            throw new ApiException("Async error: " + response.statusCode());
                        }
                        return Arrays.asList(mapper.readValue(response.body(), Post[].class));
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                });
    }
}