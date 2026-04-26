package com.vin.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vin.dao.UserDAO;
import com.vin.dto.UserDTO;
import com.vin.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserDAO userDAO;

    public User create(UserDTO dto) {
        return userDAO.save(map(dto));
    }

    public List<User> getAll(int page, int size, String sortBy) {
        return userDAO.findAll(page, size, sortBy);
    }

    public User getById(Long id) {
        User user = userDAO.findById(id);
        if (user == null) throw new RuntimeException("User not found");
        return user;
    }

    public User update(Long id, UserDTO dto) {
        User user = getById(id);
        user.setName(dto.getName());
        user.setEmail(dto.getEmail());
        user.setAge(dto.getAge());
        return userDAO.update(user);
    }

    public void delete(Long id) {
        userDAO.delete(id);
    }

    private User map(UserDTO dto) {
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .age(dto.getAge())
                .build();
    }
}