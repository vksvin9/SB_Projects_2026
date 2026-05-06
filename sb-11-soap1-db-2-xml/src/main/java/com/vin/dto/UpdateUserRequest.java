package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "updateUserRequest", namespace = "http://vin.com/user")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UpdateUserRequest {

    @XmlElement(name = "id", namespace = "http://vin.com/user", required = true)
    private Long id;

    @XmlElement(name = "name", namespace = "http://vin.com/user", required = true)
    private String name;

    @XmlElement(name = "email", namespace = "http://vin.com/user", required = true)
    private String email;
}