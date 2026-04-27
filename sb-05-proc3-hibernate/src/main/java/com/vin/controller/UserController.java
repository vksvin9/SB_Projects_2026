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

    @PostMapping
    public ApiResponse<?> create(@RequestBody Map<String, String> body) {
        service.createUser(body.get("name"), body.get("email"));
        return ApiResponse.success("User created", null);
    }

    @PutMapping("/{id}")
    public ApiResponse<?> update(@PathVariable Long id,
                                @RequestBody Map<String, String> body) {
        service.updateUser(id, body.get("name"), body.get("email"));
        return ApiResponse.success("User updated", null);
    }

    @GetMapping("/nocursor1/{id}")
    public ApiResponse<?> noCursor1(@PathVariable Long id) {
        return ApiResponse.success(service.getUserNoCursor1(id));
    }

    @GetMapping("/nocursor2/{id}")
    public ApiResponse<?> noCursor2(@PathVariable Long id) {
        return ApiResponse.success(service.getUserEmail(id));
    }

    @GetMapping("/cursor1/{id}")
    public ApiResponse<?> cursor1(@PathVariable Long id) {
        return ApiResponse.success(service.getUserCursor1(id));
    }

    @GetMapping("/cursor2/{id}")
    public ApiResponse<?> cursor2(@PathVariable Long id) {
        return ApiResponse.success(service.getUserCursor2(id));
    }
}