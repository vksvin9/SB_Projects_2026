package com.vin.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vin.dto.UserDTO;
import com.vin.entity.User;
import com.vin.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    // CREATE
    @PostMapping
    public User create(@Valid @RequestBody UserDTO dto) {
        return service.create(dto);
    }

    // READ ALL (Pagination + Sorting)
    @GetMapping
    public List<User> getAll(
            @RequestParam int page,
            @RequestParam int size,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        return service.getAll(page, size, sortBy);
    }

    // READ BY ID
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id) {
        return service.getById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public User update(
            @PathVariable Long id,
            @Valid @RequestBody UserDTO dto
    ) {
        return service.update(id, dto);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "User deleted successfully";
    }
}
