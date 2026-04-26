package com.vin.repository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.vin.entity.User;
import com.vin.mapper.UserRowMapper;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;
    private final UserRowMapper rowMapper;

    public int save(User user) {
        String sql = "INSERT INTO users(name, email, age) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getAge());
    }

    public List<User> findAll(int page, int size, String sortBy) {
        int offset = page * size;
        String sql = "SELECT * FROM users ORDER BY " + sortBy + " LIMIT ? OFFSET ?";
        return jdbcTemplate.query(sql, rowMapper, size, offset);
    }

    public User findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    public int update(Long id, User user) {
        String sql = "UPDATE users SET name=?, email=?, age=? WHERE id=?";
        return jdbcTemplate.update(sql,
                user.getName(),
                user.getEmail(),
                user.getAge(),
                id);
    }

    public int delete(Long id) {
        return jdbcTemplate.update("DELETE FROM users WHERE id=?", id);
    }
}