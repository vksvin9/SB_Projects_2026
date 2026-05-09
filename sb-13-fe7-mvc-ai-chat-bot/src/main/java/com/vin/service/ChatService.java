package com.vin.service;

import reactor.core.publisher.Flux;

public interface ChatService {

    String ask(String prompt);

    Flux<String> stream(String prompt);
}