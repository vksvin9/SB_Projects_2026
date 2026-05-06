package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "UpdateUserResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="UpdateUserResponseType")
@Data
public class UpdateUserResponse {

    private Long id;
    private String name;
    private String email;
}