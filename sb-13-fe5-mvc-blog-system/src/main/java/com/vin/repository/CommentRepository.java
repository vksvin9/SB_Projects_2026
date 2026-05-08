package com.vin.repository;

import com.vin.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository
        extends JpaRepository<Comment, Long> {
}