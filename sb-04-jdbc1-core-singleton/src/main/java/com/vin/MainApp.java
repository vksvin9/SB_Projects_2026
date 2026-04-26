package com.vin;

import com.vin.dto.UserDTO;
import com.vin.service.UserService;
import com.vin.util.DBInitializer;

public class MainApp {

    public static void main(String[] args) {

        DBInitializer.init();

        UserService service = new UserService();

        UserDTO dto = new UserDTO();
        dto.setName("Vin");
        dto.setEmail("vin@test.com");

        service.create(dto);

        System.out.println(service.getAll(0, 5, "id"));
    }
}