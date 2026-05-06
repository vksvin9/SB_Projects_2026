package com.vin.controller;

import com.vin.common.ApiResponse;
import com.vin.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    // API1
    @PostMapping("/register-user")
    public ApiResponse<?> registerUser(@RequestParam String user,
                                       @RequestParam String password) {
        service.registerUser(user, password);
        return ApiResponse.builder().status("SUCCESS").message("User registered").build();
    }

    // API2
    @PostMapping("/register-api")
    public ApiResponse<?> registerApi(@RequestParam String user,
                                      @RequestParam String api) {
        service.registerApi(user, api);
        return ApiResponse.builder().status("SUCCESS").message("API registered").build();
    }

    // API3
    @PostMapping("/login")
    public ApiResponse<?> login(@RequestParam String user,
                               @RequestParam String password) {
        String token = service.login(user, password);
        return ApiResponse.builder().status("SUCCESS").data(token).build();
    }

    // API4
    @PostMapping("/auth-in")
    public ApiResponse<?> authIn(@RequestParam String token,
                                @RequestParam String uuid,   // ✅ added
                                @RequestParam String api,
                                @RequestParam String ip,
                                @RequestBody String input) {

        service.authIn(token, uuid, api, ip, input);

        return ApiResponse.builder()
                .status("SUCCESS")
                .data(uuid)   // echo back
                .build();
    }

    // API5
    @PostMapping("/auth-out")
    public ApiResponse<?> authOut(@RequestParam String uuid,
                                 @RequestBody String output) {
        service.authOut(uuid, "encryptedIn", output, 100);
        return ApiResponse.builder().status("SUCCESS").build();
    }
}