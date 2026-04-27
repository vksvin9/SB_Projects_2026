package com.vin.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "OTP_VERIFICATION", schema = "USER_DB")
@Data
public class OtpVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mobile;
    private String otp;

    @Column(name = "expiry_time")
    private LocalDateTime expiryTime;

    private Integer verified;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}