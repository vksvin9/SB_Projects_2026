package com.vin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vin.entity.OtpVerification;

public interface OtpRepository extends JpaRepository<OtpVerification, Long> {

    Optional<OtpVerification> findTopByMobileOrderByIdDesc(String mobile);
}