package com.vin.controller;

import com.vin.common.ApiResponse;
import com.vin.entity.User;
import com.vin.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<User> create(@RequestBody User user) {
        return ApiResponse.success(service.save(user));
    }

    @GetMapping
    public ApiResponse<List<User>> getAll() {
        return ApiResponse.success(service.findAll());
    }
}