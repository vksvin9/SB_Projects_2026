package com.vin.controller;

import com.vin.common.ApiResponse;
import com.vin.model.Post;
import com.vin.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class DemoController {

    private final PostService postService;

    @GetMapping("/http/posts")
    public ApiResponse<List<Post>> getPosts() {

        List<Post> posts = postService.getPosts();

        return ApiResponse.<List<Post>>builder()
                .success(true)
                .message("Posts fetched successfully")
                .data(posts)
                .build();
    }
    
    @GetMapping("/rest/posts")
    public ApiResponse<List<Post>> getPostsRestTemplate() {

        return ApiResponse.<List<Post>>builder()
                .success(true)
                .message("Posts fetched via RestTemplate")
                .data(postService.getPostsUsingRestTemplate())
                .build();
    }
    
    @GetMapping("/java-httpclient/posts")
    public ApiResponse<List<Post>> javaHttpClient() {
        return ApiResponse.<List<Post>>builder()
                .success(true)
                .message("Java HttpClient")
                .data(postService.getPostsJavaHttpClient())
                .build();
    }
    
    @GetMapping("/webclient/posts")
    public ApiResponse<List<Post>> webclient() {
        return ApiResponse.<List<Post>>builder()
                .success(true)
                .message("Posts via WebClient")
                .data(postService.getPostsUsingWebClient())
                .build();
    }
    
    @GetMapping("/feign/posts")
    public ApiResponse<List<Post>> feign() {
        return ApiResponse.<List<Post>>builder()
                .success(true)
                .message("Posts via Feign")
                .data(postService.getPostsUsingFeign())
                .build();
    }

    @GetMapping("/apache/posts")
    public ApiResponse<List<Post>> apache() {
        return ApiResponse.<List<Post>>builder()
                .success(true)
                .message("Apache HttpClient")
                .data(postService.getPostsApache())
                .build();
    }

    @GetMapping("/okhttp/posts")
    public ApiResponse<List<Post>> okhttp() {
        return ApiResponse.<List<Post>>builder()
                .success(true)
                .message("OkHttp")
                .data(postService.getPostsOkHttp())
                .build();
    }
}