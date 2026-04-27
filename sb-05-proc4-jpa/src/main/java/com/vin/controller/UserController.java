package com.vin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vin.response.ApiResponse;
import com.vin.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping
    public ApiResponse<?> insert(@RequestParam String name,
                                 @RequestParam String email) {

        service.insert(name, email);
        return ApiResponse.success(null, "User inserted");
    }

    @PutMapping
    public ApiResponse<?> update(@RequestParam Long id,
                                 @RequestParam String name,
                                 @RequestParam String email) {

        service.update(id, name, email);
        return ApiResponse.success(null, "User updated");
    }

    @GetMapping("/no-cursor/{id}")
    public ApiResponse<?> getNoCursor(@PathVariable Long id) {
        return ApiResponse.success(service.getNoCursor1(id), "Fetched");
    }

    @GetMapping("/email/{id}")
    public ApiResponse<?> getEmail(@PathVariable Long id) {
        return ApiResponse.success(service.getEmail(id), "Fetched");
    }

    @GetMapping("/cursor1/{id}")
    public ApiResponse<?> getCursor1(@PathVariable Long id) {
        return ApiResponse.success(service.getCursor1(id), "Fetched");
    }

    @GetMapping("/cursor2/{id}")
    public ApiResponse<?> getCursor2(@PathVariable Long id) {
        return ApiResponse.success(service.getCursor2(id), "Fetched");
    }
}