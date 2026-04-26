package com.vin.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.vin.dto.UserMapper;
import com.vin.dto.UserRequestDTO;
import com.vin.dto.UserResponseDTO;
import com.vin.entity.User;
import com.vin.exception.ResourceNotFoundException;
import com.vin.repo.UserRepository;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository repo;

    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    private String normalize(String email) {
        return email.trim().toLowerCase();
    }

    @Override
    public UserResponseDTO create(UserRequestDTO dto) {

        User user = UserMapper.toEntity(dto);
        user.setEmail(normalize(dto.getEmail()));

        return UserMapper.toDTO(repo.save(user));
    }

    @Override
    public UserResponseDTO getById(Long id) {
        return repo.findById(id)
                .map(UserMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
    }

    @Override
    public Page<UserResponseDTO> getAll(int page, int size, String sortBy, String direction) {

        Pageable pageable = buildPageable(page, size, sortBy, direction);

        return repo.findAll(pageable)
                .map(UserMapper::toDTO);
    }

    @Override
    public UserResponseDTO update(Long id, UserRequestDTO dto) {

        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        user.setName(dto.getName());
        user.setEmail(normalize(dto.getEmail()));

        return UserMapper.toDTO(repo.save(user));
    }

    @Override
    public void delete(Long id) {

        User user = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        repo.delete(user);
    }

    @Override
    public Page<UserResponseDTO> search(String keyword, int page, int size, String sortBy, String direction) {

        Pageable pageable = buildPageable(page, size, sortBy, direction);

        return repo
                .findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
                        keyword,
                        keyword,
                        pageable
                )
                .map(UserMapper::toDTO);
    }

    private Pageable buildPageable(int page, int size, String sortBy, String direction) {

        page = Math.max(page, 0);
        size = Math.min(size, 50);

        List<String> allowed = List.of("id", "name", "email");
        if (!allowed.contains(sortBy)) sortBy = "id";

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        return PageRequest.of(page, size, sort);
    }
}