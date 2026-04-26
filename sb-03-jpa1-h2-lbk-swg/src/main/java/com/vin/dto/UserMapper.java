package com.vin.dto;

import com.vin.entity.User;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        return new User(dto.getId(), dto.getName(), dto.getEmail());
    }

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setName(user.getName());
        dto.setEmail(user.getEmail());
        return dto;
    }
}