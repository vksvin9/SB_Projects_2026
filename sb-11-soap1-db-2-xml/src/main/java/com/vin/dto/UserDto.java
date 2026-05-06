package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class UserDto {

    @XmlElement(namespace = "http://vin.com/user")
    private Long id;

    @XmlElement(namespace = "http://vin.com/user")
    private String name;

    @XmlElement(namespace = "http://vin.com/user")
    private String email;
}