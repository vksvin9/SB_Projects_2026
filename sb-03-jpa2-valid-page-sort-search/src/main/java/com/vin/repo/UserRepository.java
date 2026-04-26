package com.vin.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

    // 🔥 FIXED SEARCH (name OR email)
    Page<User> findByNameContainingIgnoreCaseOrEmailContainingIgnoreCase(
            String name,
            String email,
            Pageable pageable
    );
}