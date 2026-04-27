ALTER SESSION SET CURRENT_SCHEMA = user_db;

ALTER TABLE users ADD (
    photo BLOB,
    photo_name VARCHAR2(255),
    photo_type VARCHAR2(100)
);

===OR===

ALTER TABLE user_db.users ADD (
    photo BLOB,
    photo_name VARCHAR2(255),
    photo_type VARCHAR2(100)
);

========
#Create Table (with schema)
CREATE TABLE USER_DB.OTP_VERIFICATION (
    ID NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    MOBILE VARCHAR2(15) NOT NULL,
    OTP VARCHAR2(6) NOT NULL,
    EXPIRY_TIME TIMESTAMP NOT NULL,
    VERIFIED NUMBER(1) DEFAULT 0,
    CREATED_AT TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
#Index
CREATE INDEX USER_DB.IDX_OTP_MOBILE ON USER_DB.OTP_VERIFICATION(MOBILE);
#Insert
INSERT INTO USER_DB.OTP_VERIFICATION(MOBILE, OTP, EXPIRY_TIME, VERIFIED)VALUES ('9876543210', '123456', SYSTIMESTAMP + INTERVAL '5' MINUTE, 0);
#Select
SELECT * FROM USER_DB.OTP_VERIFICATION;
#Delete expired
DELETE FROM USER_DB.OTP_VERIFICATION
WHERE EXPIRY_TIME < SYSTIMESTAMP;
#Grant access (if needed)
GRANT SELECT, INSERT, UPDATE, DELETE ON USER_DB.OTP_VERIFICATION TO SYSTEM;
GRANT SELECT, INSERT, UPDATE, DELETE ON USER_DB.OTP_VERIFICATION TO APP_USER;
#Drop table
DROP TABLE USER_DB.OTP_VERIFICATION;

========
Below are clear, end-to-end steps to integrate Twilio for OTP SMS in your Spring Boot project.
1) Create Twilio Account
Sign up → Console
Copy:
Account SID
Auth Token
Get a Twilio phone number
2) Verify Your Phone (Trial requirement)
Console → Phone Numbers → Verified Caller IDs
Add your number (e.g., +919876543210)
Verify via OTP
3) Add Maven Dependency
<dependency>
    <groupId>com.twilio.sdk</groupId>
    <artifactId>twilio</artifactId>
    <version>9.16.0</version>
</dependency>
4) application.yml
twilio:
  account-sid: YOUR_ACCOUNT_SID
  auth-token: YOUR_AUTH_TOKEN
  from-number: YOUR_TWILIO_NUMBER
5) Config Class
package com.vin.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "twilio")
public class TwilioConfig {
    private String accountSid;
    private String authToken;
    private String fromNumber;
}
6) SMS Service
package com.vin.service;

import com.vin.config.TwilioConfig;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TwilioSmsService {

    private final TwilioConfig config;

    @PostConstruct
    public void init() {
        Twilio.init(config.getAccountSid(), config.getAuthToken());
    }

    public void sendOtp(String mobile, String otp) {

        String to = "+91" + mobile; // E.164 format
        String body = "Your OTP is " + otp + ". Do not share it.";

        Message.creator(
                new PhoneNumber(to),
                new PhoneNumber(config.getFromNumber()),
                body
        ).create();
    }
}
7) Update Your OtpService
private final TwilioSmsService smsService;

public String sendOtp(String mobile) {

    String otp = String.valueOf((int)(Math.random() * 900000) + 100000);

    // save OTP in DB

    smsService.sendOtp(mobile, otp);

    return "OTP sent";
}
8) Test API
POST /api/otp/send?mobile=9876543210