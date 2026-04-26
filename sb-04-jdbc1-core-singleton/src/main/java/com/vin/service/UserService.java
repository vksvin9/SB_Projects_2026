package com.vin.service;

import java.util.List;

import com.vin.dto.UserDTO;
import com.vin.entity.User;
import com.vin.exception.GlobalException;
import com.vin.repository.UserRepository;

public class UserService {

    private UserRepository repo = new UserRepository();

    public void create(UserDTO dto) {
        if (dto.getName() == null || dto.getEmail() == null) {
            throw new GlobalException("Invalid input");
        }

        try {
            repo.save(new User(0, dto.getName(), dto.getEmail()));
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }

    public List<User> getAll(int page, int size, String sortBy) {
        try {
            return repo.findAll(page, size, sortBy);
        } catch (Exception e) {
            throw new GlobalException(e.getMessage());
        }
    }
}