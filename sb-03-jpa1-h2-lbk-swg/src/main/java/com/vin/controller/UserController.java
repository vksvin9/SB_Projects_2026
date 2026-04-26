package com.vin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.dto.UserDTO;
import com.vin.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User API", description = "CRUD operations for users")
public class UserController {

    private final UserService service;

    @PostMapping
    @Operation(summary = "Create user")
    public UserDTO create(@RequestBody UserDTO dto) {
        return service.create(dto);
    }

    @GetMapping
    @Operation(summary = "Get all user")
    public List<UserDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get user by id")
    public UserDTO getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public UserDTO update(@PathVariable Long id, @RequestBody UserDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "Deleted successfully";
    }
}