package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "CreateUserResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CreateUserResponseType")   // ✅ IMPORTANT
@Data
public class CreateUserResponse {

    private Long id;
    private String name;
    private String email;
}