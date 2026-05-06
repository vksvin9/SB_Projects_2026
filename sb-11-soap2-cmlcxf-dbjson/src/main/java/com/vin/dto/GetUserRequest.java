package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "GetUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class GetUserRequest {
    private Long id;
}