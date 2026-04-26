package com.vin.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.vin.dao.UserProcedureRepository;

@Service
public class UserService {

    private final UserProcedureRepository repo;

    public UserService(UserProcedureRepository repo) {
        this.repo = repo;
    }

    public void createUser(String name, String email) {
        repo.insertUser(name, email);
    }

    public void updateUser(Long id, String name, String email) {
        repo.updateUser(id, name, email);
    }

    public Map<String, Object> getUser(Long id) {
        return repo.getUserCursor1(id);
    }
}
