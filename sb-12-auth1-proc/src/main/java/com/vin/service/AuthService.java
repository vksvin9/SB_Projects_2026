package com.vin.service;

import org.springframework.stereotype.Service;

import com.vin.repository.AuthRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository repo;

    public void registerUser(String user, String password) {
        repo.registerUser(user, password);
    }

    public void registerApi(String user, String api) {
        repo.registerApi(user, api);
    }

    public String login(String user, String password) {
        return repo.getToken(user, password);
    }

    public void authIn(String token, String uuid, String api, String ip, String input) {
        repo.authIn(token, uuid, api, ip, input, 10);
    }

    public void authOut(String uuid, String in, String out, long time) {
        repo.authOut(uuid, in, out, time);
    }
}