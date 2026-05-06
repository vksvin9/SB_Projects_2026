package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "DeleteUserRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class DeleteUserRequest {
    private Long id;
}