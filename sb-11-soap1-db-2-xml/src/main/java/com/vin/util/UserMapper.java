package com.vin.util;

import com.vin.dto.UserDto;
import com.vin.entity.User;

public class UserMapper {

    public static UserDto toDto(User u) {
        UserDto d = new UserDto();
        d.setId(u.getId());
        d.setName(u.getName());
        d.setEmail(u.getEmail());
        return d;
    }
}