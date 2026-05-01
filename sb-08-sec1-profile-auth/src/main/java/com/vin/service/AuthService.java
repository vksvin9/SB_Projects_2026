package com.vin.service;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.vin.common.RegisterRequest;
import com.vin.entity.User;
import com.vin.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;
    private final PasswordEncoder encoder;

    public User register(RegisterRequest req) {

        long start = System.currentTimeMillis();
        log.info("SERVICE START -> registerUser | username={}", req.getUsername());

        // ✅ 1. Validate Role (IMPORTANT)
        String inputRole = req.getRole().toUpperCase();
        if (!List.of("ADMIN", "USER").contains(inputRole)) {
            throw new RuntimeException("Invalid role");
        }

        // ✅ 2. Check Duplicate Username
        repo.findByUsername(req.getUsername())
                .ifPresent(u -> {
                    throw new RuntimeException("Username already exists");
                });

        // ✅ 3. Prepare Role (Spring format)
        String role = "ROLE_" + inputRole;

        // ✅ 4. Encode Password
        String encodedPassword = encoder.encode(req.getPassword());

        // ✅ 5. Build User
        User user = User.builder()
                .username(req.getUsername())
                .password(encodedPassword)
                .role(role)
                .build();

        // ✅ 6. Save
        log.info("DB CALL -> save user");
        User saved = repo.save(user);

        log.info("SERVICE END -> registerUser | id={} | time={}ms",
                saved.getId(),
                System.currentTimeMillis() - start);

        return saved;
    }
}