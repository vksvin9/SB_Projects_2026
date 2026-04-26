package com.vin.service;

import org.springframework.stereotype.Service;

import com.vin.dao.AppRepository;
import com.vin.dao.UserProcedureRepository;

@Service
public class AppService {

    private final UserProcedureRepository userRepo;
    private final AppRepository appRepo;

    public AppService(UserProcedureRepository userRepo,
                      AppRepository appRepo) {
        this.userRepo = userRepo;
        this.appRepo = appRepo;
    }

    public void createUserWithLog(String name, String email) {

        userRepo.insertUser(name, email);           // user_db
        appRepo.insertLog("User created: " + name); // app_user
    }
}