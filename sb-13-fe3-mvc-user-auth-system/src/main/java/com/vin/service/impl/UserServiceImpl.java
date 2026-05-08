package com.vin.service.impl;

import com.vin.entity.User;
import com.vin.repository.UserRepository;
import com.vin.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder =
            new BCryptPasswordEncoder();

    @Override
    public User register(User user) {

        user.setPassword(
                encoder.encode(user.getPassword())
        );

        return userRepository.save(user);
    }

    @Override
    public User login(String username, String password) {

        User user = userRepository
                .findByUsername(username)
                .orElse(null);

        if (
                user != null &&
                encoder.matches(password, user.getPassword())
        ) {
            return user;
        }

        return null;
    }
}