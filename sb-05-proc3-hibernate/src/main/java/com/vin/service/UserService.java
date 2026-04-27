package com.vin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vin.dao.UserProcedureRepository;

import java.util.Map;

@Service
@Transactional
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

    public Map<String, String> getUserNoCursor1(Long id) {
        return repo.getUserNoCursor1(id);
    }

    public String getUserEmail(Long id) {
        return repo.getUserNoCursor2(id);
    }

    public Map<String, Object> getUserCursor1(Long id) {
        return repo.getUserCursor1(id);
    }

    public Map<String, Object> getUserCursor2(Long id) {
        return repo.getUserCursor2(id);
    }
}