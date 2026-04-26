package com.vin.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.vin.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.addresses LEFT JOIN FETCH u.courses")
    List<User> findAllWithRelations();
}