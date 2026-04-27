package com.vin.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.vin.entity.OtpVerification;
import com.vin.repository.OtpRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OtpService {

    private final OtpRepository repo;
    private final TwilioSmsService smsService;
    
    public String sendOtp(String mobile) {

        String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

        OtpVerification entity = new OtpVerification();
        entity.setMobile(mobile);
        entity.setOtp(otp);
        entity.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        entity.setVerified(0);
        entity.setCreatedAt(LocalDateTime.now());

        repo.save(entity);

        smsService.sendOtp(mobile, otp);

        return "OTP sent";
    }
    // Verify OTP
    public String verifyOtp(String mobile, String otp) {

        OtpVerification record = repo.findTopByMobileOrderByIdDesc(mobile)
                .orElseThrow(() -> new RuntimeException("OTP not found"));

        if (record.getVerified() == 1) {
            return "Already verified";
        }

        if (!record.getOtp().equals(otp)) {
            return "Invalid OTP";
        }

        if (record.getExpiryTime().isBefore(LocalDateTime.now())) {
            return "OTP expired";
        }

        record.setVerified(1);
        repo.save(record);

        return "OTP verified";
    }
}