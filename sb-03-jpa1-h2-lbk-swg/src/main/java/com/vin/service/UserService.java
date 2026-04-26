package com.vin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vin.dto.UserDTO;
import com.vin.dto.UserMapper;
import com.vin.entity.User;
import com.vin.exception.ResourceNotFoundException;
import com.vin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;

    public UserDTO create(UserDTO dto) {
        User user = repository.save(UserMapper.toEntity(dto));
        return UserMapper.toDTO(user);
    }

    public List<UserDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(UserMapper::toDTO)
                .toList();
    }

    public UserDTO getById(Long id) {
        User user = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return UserMapper.toDTO(user);
    }

    public UserDTO update(Long id, UserDTO dto) {
        User existing = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        return UserMapper.toDTO(repository.save(existing));
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("User not found");
        }
        repository.deleteById(id);
    }
}