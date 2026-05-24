package com.vin.service;

import com.vin.event.UserCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailSenderService {

    public void sendWelcomeEmail(
            UserCreatedEvent event) {

        log.info(
                """
                =========================================
                SENDING WELCOME EMAIL
                
                User ID    : {}
                User Name  : {}
                User Email : {}
                
                Welcome email sent successfully
                =========================================
                """,
                event.getId(),
                event.getName(),
                event.getEmail()
        );

    }

}