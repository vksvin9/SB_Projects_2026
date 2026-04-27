package com.vin.client;

import com.vin.exception.ApiException;
import com.vin.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RestTemplateClient {

    private final RestTemplate restTemplate;

    @Value("${api.base-url}")
    private String baseUrl;

    public List<Post> getPosts() {

        try {
            String url = baseUrl + "/posts";

            ResponseEntity<Post[]> response =
                    restTemplate.getForEntity(url, Post[].class);

            if (!response.getStatusCode().is2xxSuccessful()) {
                throw new ApiException("External API error: " + response.getStatusCode());
            }

            return Arrays.asList(response.getBody());

        } catch (Exception e) {
            throw new ApiException("RestTemplate call failed", e);
        }
    }
}