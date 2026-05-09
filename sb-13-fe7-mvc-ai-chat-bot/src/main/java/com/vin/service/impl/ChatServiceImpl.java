package com.vin.service.impl;

import com.vin.service.ChatService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChatServiceImpl implements ChatService {

    private final ChatClient chatClient;

    @Override
    public String ask(String prompt) {

        long start = System.currentTimeMillis();
        log.info("Incoming prompt: {}", prompt);

        try {
            String systemPrompt = """
                    You are a senior Java and Spring Boot expert.
                    Explain concepts in a clear, beginner-friendly manner.
                    Provide practical examples when helpful.
                    Keep responses well-structured and concise.
                    """;

            String response = chatClient
                    .prompt()
                    .system(systemPrompt)
                    .user(prompt)
                    .call()
                    .content();

            long duration = System.currentTimeMillis() - start;
            log.info("AI response generated in {} ms", duration);

            return response;

        } catch (Exception ex) {
            log.error("Error while calling Ollama", ex);
            throw ex;
        }
    }

    @Override
    public Flux<String> stream(String prompt) {

        String systemPrompt = """
                You are a senior Java and Spring Boot expert.
                Explain concepts in a clear, beginner-friendly manner.
                Provide practical examples when helpful.
                Keep responses well-structured and concise.
                """;

        return chatClient
                .prompt()
                .system(systemPrompt)
                .user(prompt)
                .stream()
                .content();
    }
}