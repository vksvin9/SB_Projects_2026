package com.vin.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.vin.entity.User;
import com.vin.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PhotoService {

    private final UserRepository repo;

    public void upload(Long id, MultipartFile file) throws Exception {
        User user = repo.findById(id).orElseThrow();

        user.setPhoto(file.getBytes());
        user.setPhotoName(file.getOriginalFilename());
        user.setPhotoType(file.getContentType());

        repo.save(user);
    }

    public User get(Long id) {
        return repo.findById(id).orElseThrow();
    }
}