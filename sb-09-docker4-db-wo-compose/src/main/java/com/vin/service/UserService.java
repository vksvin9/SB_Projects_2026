package com.vin.service;

import com.vin.entity.User;
import com.vin.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public User save(User user) {
        return repo.save(user);
    }

    public List<User> findAll() {
        return repo.findAll();
    }
}