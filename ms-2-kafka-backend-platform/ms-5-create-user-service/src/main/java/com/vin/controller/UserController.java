package com.vin.controller;

import com.vin.dto.ApiResponse;
import com.vin.dto.CreateUserRequest;
import com.vin.event.UserCreatedEvent;
import com.vin.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ApiResponse<UserCreatedEvent> createUser(
            @Valid
            @RequestBody
            CreateUserRequest request) {

        return userService.createUser(request);
    }

}