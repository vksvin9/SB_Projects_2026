package com.vin.consumer;

import com.vin.event.UserCreatedEvent;
import com.vin.service.EmailSenderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserCreatedConsumer {

    private final EmailSenderService emailSenderService;

    @KafkaListener(
            topics = "${kafka.topic.user-created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeUserCreatedEvent(
            UserCreatedEvent event) {

        log.info(
                "USER_CREATED event received for email={}",
                event.getEmail()
        );

        emailSenderService.sendWelcomeEmail(event);

    }

}