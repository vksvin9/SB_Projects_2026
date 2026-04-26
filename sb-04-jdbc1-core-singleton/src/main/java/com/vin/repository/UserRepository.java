package com.vin.repository;

import java.sql.*;
import java.util.*;

import com.vin.entity.User;
import com.vin.mapper.UserRowMapper;
import com.vin.util.DBConnection;

public class UserRepository {

    private Connection conn = DBConnection.getInstance();

    public void save(User user) throws Exception {
        String sql = "INSERT INTO users(name, email) VALUES (?, ?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.executeUpdate();
    }

    public List<User> findAll(int page, int size, String sortBy) throws Exception {
        String sql = "SELECT * FROM users ORDER BY " + sortBy + " LIMIT ? OFFSET ?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setInt(1, size);
        ps.setInt(2, page * size);

        ResultSet rs = ps.executeQuery();

        List<User> list = new ArrayList<>();
        UserRowMapper mapper = new UserRowMapper();

        while (rs.next()) {
            list.add(mapper.mapRow(rs));
        }
        return list;
    }

    public User findById(int id) throws Exception {
        String sql = "SELECT * FROM users WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new UserRowMapper().mapRow(rs);
        }
        return null;
    }

    public void update(User user) throws Exception {
        String sql = "UPDATE users SET name=?, email=? WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);

        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        ps.setInt(3, user.getId());

        ps.executeUpdate();
    }

    public void delete(int id) throws Exception {
        String sql = "DELETE FROM users WHERE id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, id);
        ps.executeUpdate();
    }
}