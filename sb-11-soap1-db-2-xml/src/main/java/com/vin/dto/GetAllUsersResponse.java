package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

import java.util.List;

@XmlRootElement(name = "getAllUsersResponse", namespace = "http://vin.com/user")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class GetAllUsersResponse {

    private List<UserDto> users;
}