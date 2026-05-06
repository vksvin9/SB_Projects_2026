package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "deleteUserRequest", namespace = "http://vin.com/user")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class DeleteUserRequest {

    @XmlElement(name = "id", namespace = "http://vin.com/user", required = true)
    private Long id;
}