package com.vin.controller;

import com.vin.service.RedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/redis")
@RequiredArgsConstructor
public class RedisController {

    private final RedisService redisService;

    @PostMapping("/save")
    public Mono<String> save(
            @RequestParam String key,
            @RequestParam String value) {

        return redisService
                .save(key, value, 300)
                .map(saved -> "Data Saved Into Redis");
    }

    @GetMapping("/get")
    public Mono<String> get(
            @RequestParam String key) {

        return redisService.get(key);
    }

    @DeleteMapping("/delete")
    public Mono<String> delete(
            @RequestParam String key) {

        return redisService
                .delete(key)
                .map(deleted -> "Redis Key Deleted");
    }
}