package com.vin.controller;

import com.vin.common.ApiResponse;
import com.vin.service.PostService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PostController {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @GetMapping("/posts")
    public ApiResponse<?> getPosts() {
        return ApiResponse.ok(service.getPosts());
    }
}