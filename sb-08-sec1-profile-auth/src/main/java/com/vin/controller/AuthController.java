package com.vin.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.common.ApiResponse;
import com.vin.common.RegisterRequest;
import com.vin.entity.User;
import com.vin.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ApiResponse<?> register(@Valid @RequestBody RegisterRequest req) {

        long start = System.currentTimeMillis();
        log.info("API IN -> POST /auth/register | username={}", req.getUsername());

        User saved = service.register(req);

        log.info("API OUT -> POST /auth/register | time={}ms",
                System.currentTimeMillis() - start);

        return ApiResponse.builder()
                .success(true)
                .message("User registered")
                .data(saved.getUsername())
                .build();
    }
}