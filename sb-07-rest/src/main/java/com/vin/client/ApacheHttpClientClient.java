package com.vin.client;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.core5.http.ClassicHttpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vin.exception.ApiException;
import com.vin.model.Post;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ApacheHttpClientClient {

    private final CloseableHttpClient httpClient;
    private final ObjectMapper mapper = new ObjectMapper();

    @Value("${api.base-url}")
    private String baseUrl;

    public List<Post> getPosts() {

        try {
            HttpGet request = new HttpGet(baseUrl + "/posts");

            ClassicHttpResponse response = httpClient.executeOpen(null, request, null);

            if (response.getCode() >= 300) {
                throw new ApiException("Apache error: " + response.getCode());
            }

            InputStream body = response.getEntity().getContent();

            return Arrays.asList(mapper.readValue(body, Post[].class));

        } catch (Exception e) {
            throw new ApiException("Apache HttpClient failed", e);
        }
    }
}