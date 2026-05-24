package com.vin.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class KafkaTopicConfig {

    @Value("${kafka.topic.user-created}")
    private String userCreatedTopic;

    @Bean
    public NewTopic userCreatedTopic() {

        return new NewTopic(
                userCreatedTopic,
                1,
                (short) 1
        );
    }

}