package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "getUserRequest", namespace = "http://vin.com/user")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class GetUserRequest {

    @XmlElement(name = "id", namespace = "http://vin.com/user", required = true)
    private Long id;
}