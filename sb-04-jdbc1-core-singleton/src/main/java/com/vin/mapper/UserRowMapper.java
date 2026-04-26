package com.vin.mapper;

import java.sql.ResultSet;

import com.vin.entity.User;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs) throws Exception {
        return new User(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("email"));
    }
}