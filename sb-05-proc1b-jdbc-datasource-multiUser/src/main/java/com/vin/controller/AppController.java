package com.vin.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vin.common.ApiResponse;
import com.vin.service.AppService;

@RestController
@RequestMapping("/app")
public class AppController {

    private final AppService service;

    public AppController(AppService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ApiResponse<?> create(@RequestBody Map<String, String> body) {

        service.createUserWithLog(
                body.get("name"),
                body.get("email")
        );

        return ApiResponse.success("User + Log created", null);
    }
}