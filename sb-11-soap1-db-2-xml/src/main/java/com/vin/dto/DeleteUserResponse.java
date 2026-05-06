package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "deleteUserResponse", namespace = "http://vin.com/user")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class DeleteUserResponse {

    private String status;
}