package com.vin.controller;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.common.ApiResponse;
import com.vin.entity.User;
import com.vin.repository.UserRepository;
import com.vin.security.JwtUtil;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository repo;
    private final PasswordEncoder encoder;
    private final JwtUtil jwtUtil;

    @PostMapping("/register")
    public ApiResponse<?> register(@RequestBody User user) {

        log.info("API IN -> register");

        user.setPassword(encoder.encode(user.getPassword()));
        repo.save(user);

        log.info("API OUT -> register");

        return ApiResponse.builder().success(true).build();
    }

    @PostMapping("/login")
    public ApiResponse<?> login(@RequestBody User req) {

        var user = repo.findByUsername(req.getUsername())
                .orElseThrow();

        if (!encoder.matches(req.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtUtil.generateToken(user.getUsername(), user.getRole());

        return ApiResponse.builder()
                .success(true)
                .data(token)
                .build();
    }
}