package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "UpdateUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UpdateUserRequest {
    private Long id;
    private String name;
    private String email;
}