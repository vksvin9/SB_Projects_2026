package com.vin.controller;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import com.vin.dto.*;
import com.vin.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @PostMapping
    public ApiResponse<UserResponseDTO> create(@Valid @RequestBody UserRequestDTO dto) {
        return ApiResponse.success("User created", service.create(dto));
    }

    @GetMapping("/id/{id}") 
    public ApiResponse<UserResponseDTO> get(@PathVariable Long id) {
        return ApiResponse.success("User fetched", service.getById(id));
    }

    @GetMapping
    public ApiResponse<Page<UserResponseDTO>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ApiResponse.success("Users list",
                service.getAll(page, size, sortBy, direction));
    }

    @PutMapping("/{id}")
    public ApiResponse<UserResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody UserRequestDTO dto) {

        return ApiResponse.success("User updated",
                service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ApiResponse.success("User deleted", null);
    }

    @GetMapping("/search")
    public ApiResponse<Page<UserResponseDTO>> search(
            @RequestParam String keyword,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ApiResponse.success(
                "Search result",
                service.search(keyword, page, size, sortBy, direction)
        );
    }
}