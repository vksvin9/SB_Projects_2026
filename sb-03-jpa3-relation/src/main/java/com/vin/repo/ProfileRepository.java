package com.vin.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {}
