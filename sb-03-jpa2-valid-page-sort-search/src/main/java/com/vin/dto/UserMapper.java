package com.vin.dto;

import com.vin.entity.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setEmail(dto.getEmail());
        return u;
    }

    public static UserResponseDTO toDTO(User u) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(u.getId());
        dto.setName(u.getName());
        dto.setEmail(u.getEmail());
        return dto;
    }
}