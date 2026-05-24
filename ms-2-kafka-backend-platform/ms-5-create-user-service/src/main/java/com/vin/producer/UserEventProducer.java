package com.vin.producer;

import com.vin.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserEventProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${kafka.topic.user-created}")
    private String topic;

    public void publishUserCreatedEvent(
            UserCreatedEvent event) {

        log.info(
                "Publishing USER_CREATED event for email={}",
                event.getEmail()
        );

        kafkaTemplate.send(
                topic,
                event.getEmail(),
                event
        );
    }

}