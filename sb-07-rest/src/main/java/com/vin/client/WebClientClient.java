package com.vin.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.vin.exception.ApiException;
import com.vin.model.Post;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WebClientClient {

    private final WebClient webClient;

    @Value("${api.base-url}")
    private String baseUrl;

    public List<Post> getPosts() {

        try {
            return webClient.get()
                    .uri(baseUrl + "/posts")
                    .retrieve()

                    // handle 4xx/5xx
                    .onStatus(
                            status -> status.is4xxClientError() || status.is5xxServerError(),
                            response -> response.bodyToMono(String.class)
                                    .map(body -> new ApiException("External API error: " + body))
                    )

                    .bodyToFlux(Post.class)
                    .collectList()
                    .block(); // convert reactive → blocking (to keep contract same)

        } catch (Exception e) {
            throw new ApiException("WebClient call failed", e);
        }
    }
}