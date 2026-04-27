package com.vin.service;

import org.springframework.stereotype.Service;

import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;
import com.vin.config.TwilioConfig;

import jakarta.annotation.PostConstruct;
import com.twilio.rest.api.v2010.account.Message;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TwilioSmsService {

    private final TwilioConfig config;

    @PostConstruct
    public void init() {
        Twilio.init(
            config.getAccountSid(),
            config.getAuthToken()
        );
    }

    public void sendOtp(String mobile, String otp) {

        String to = "+91" + mobile;

        String message = "Your OTP is " + otp + ". Do not share it.";

        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(config.getFromNumber()),
                message
        ).create();
    }
}