package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "CreateUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class CreateUserRequest {
    private String name;
    private String email;
}