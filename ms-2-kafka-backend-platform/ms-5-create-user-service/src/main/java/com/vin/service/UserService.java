package com.vin.service;

import com.vin.dto.ApiResponse;
import com.vin.dto.CreateUserRequest;
import com.vin.entity.User;
import com.vin.event.UserCreatedEvent;
import com.vin.producer.UserEventProducer;
import com.vin.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserEventProducer userEventProducer;

    public ApiResponse<UserCreatedEvent> createUser(
            CreateUserRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        User savedUser = userRepository.save(user);

        UserCreatedEvent event =
                UserCreatedEvent.builder()
                        .id(savedUser.getId())
                        .name(savedUser.getName())
                        .email(savedUser.getEmail())
                        .build();

        userEventProducer.publishUserCreatedEvent(event);

        return ApiResponse.<UserCreatedEvent>builder()
                .success(true)
                .message("User created successfully")
                .data(event)
                .timestamp(LocalDateTime.now())
                .build();
    }

}