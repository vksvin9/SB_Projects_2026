package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "GetUserResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="GetUserResponseType")
@Data
public class GetUserResponse {
    private Long id;
    private String name;
    private String email;
}