package com.vin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.common.ApiResponse;
import com.vin.common.UserResponse;
import com.vin.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repo;

    @GetMapping
    public ApiResponse<List<UserResponse>> getAll() {

        long start = System.currentTimeMillis();
        log.info("API IN -> GET /users");

        List<UserResponse> list = repo.findAll().stream()
                .map(u -> new UserResponse(
                        u.getId(),
                        u.getUsername(),
                        u.getRole()
                ))
                .toList();

        log.info("API OUT -> GET /users | count={} | time={}ms",
                list.size(),
                System.currentTimeMillis() - start);

        return ApiResponse.<List<UserResponse>>builder()
                .success(true)
                .data(list)
                .build();
    }
}