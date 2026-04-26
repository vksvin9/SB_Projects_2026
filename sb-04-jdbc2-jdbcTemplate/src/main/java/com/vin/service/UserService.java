package com.vin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vin.dto.UserDTO;
import com.vin.entity.User;
import com.vin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
//Service Layer (Singleton by Default)
public class UserService {

    private final UserRepository repository;

    public void create(UserDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .build();
        repository.save(user);
    }

    public List<User> getAll(int page, int size, String sortBy) {
        return repository.findAll(page, size, sortBy);
    }

    public User getById(Long id) {
        return repository.findById(id);
    }

    public void update(Long id, UserDTO dto) {
        User user = User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .build();
        repository.update(id, user);
    }

    public void delete(Long id) {
        repository.delete(id);
    }
}