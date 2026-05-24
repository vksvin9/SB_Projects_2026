package com.vin.service;

import com.vin.dto.AuthResponse;
import com.vin.dto.LoginRequest;
import com.vin.dto.RegisterRequest;
import com.vin.entity.Role;
import com.vin.entity.User;
import com.vin.repository.UserRepository;
import com.vin.util.JwtUtil;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final JwtUtil jwtUtil;

    public void register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {

            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(
                        passwordEncoder.encode(request.getPassword())
                )
                .role(Role.USER)
                .build();

        userRepository.save(user);
    }

    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid Credentials"));

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new RuntimeException("Invalid Credentials");
        }

        return AuthResponse.builder()
                .accessToken(
                        jwtUtil.generateAccessToken(user.getEmail())
                )
                .refreshToken(
                        jwtUtil.generateRefreshToken(user.getEmail())
                )
                .tokenType("Bearer")
                .build();
    }
}