package com.vin.service;

import org.springframework.data.domain.Page;

import com.vin.dto.UserRequestDTO;
import com.vin.dto.UserResponseDTO;

public interface UserService {

    UserResponseDTO create(UserRequestDTO dto);

    UserResponseDTO getById(Long id);

    Page<UserResponseDTO> getAll(int page, int size, String sortBy, String direction);

    UserResponseDTO update(Long id, UserRequestDTO dto);

    void delete(Long id);

    Page<UserResponseDTO> search(String name, int page, int size, String sortBy, String direction);
}