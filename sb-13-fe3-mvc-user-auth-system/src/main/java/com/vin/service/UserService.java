package com.vin.service;

import com.vin.entity.User;

public interface UserService {

    User register(User user);

    User login(String username, String password);
}