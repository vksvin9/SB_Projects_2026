package com.vin.controller;

import com.vin.common.ApiResponse;
import com.vin.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    // CREATE USER
    @PostMapping
    public ApiResponse<?> create(@RequestBody Map<String, String> body) {
        service.createUser(body.get("name"), body.get("email"));
        return ApiResponse.success("User created", null);
    }

    // UPDATE USER
    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable Long id,
                                 @RequestBody Map<String, String> body) {
        service.updateUser(id, body.get("name"), body.get("email"));
        return ApiResponse.success("User updated", null);
    }

    // GET USER
    @GetMapping("/{id}")
    public ApiResponse<?> get(@PathVariable Long id) {
        return ApiResponse.success(service.getUser(id));
    }
}