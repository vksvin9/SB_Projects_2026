package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "updateUserResponse", namespace = "http://vin.com/user")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UpdateUserResponse {

    private UserDto user;
}