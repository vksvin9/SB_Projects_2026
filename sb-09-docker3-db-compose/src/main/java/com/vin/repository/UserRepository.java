package com.vin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}