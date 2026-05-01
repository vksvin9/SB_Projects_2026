package com.vin.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.vin.entity.Post;
import com.vin.repository.PostRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository repo;

    public Post create(Post post) {
        log.info("DB CALL -> save post");
        return repo.save(post);
    }

    public List<Post> getAll() {
        log.info("DB CALL -> fetch posts");
        return repo.findAll();
    }
}