package com.vin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.common.ApiResponse;
import com.vin.service.ExternalService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/external")
@RequiredArgsConstructor
public class ExternalController {

    private final ExternalService service;

    @GetMapping("/posts")
    public ApiResponse<?> getPosts() throws Exception {

        long start = System.currentTimeMillis();
        log.info("API IN -> GET /external/posts");

        String data = service.getPosts();

        log.info("API OUT -> GET /external/posts | time={}ms",
                System.currentTimeMillis() - start);

        return ApiResponse.builder()
                .success(true)
                .data(data)
                .build();
    }
}