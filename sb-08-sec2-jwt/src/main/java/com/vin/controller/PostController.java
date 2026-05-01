package com.vin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.common.ApiResponse;
import com.vin.entity.Post;
import com.vin.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService service;

    @PostMapping
    public ApiResponse<?> create(@RequestBody Post post) {
        log.info("API IN -> create post");
        return ApiResponse.builder()
                .success(true)
                .data(service.create(post))
                .build();
    }

    @GetMapping
    public ApiResponse<?> getAll() {
        log.info("API IN -> get posts");
        return ApiResponse.builder()
                .success(true)
                .data(service.getAll())
                .build();
    }
}