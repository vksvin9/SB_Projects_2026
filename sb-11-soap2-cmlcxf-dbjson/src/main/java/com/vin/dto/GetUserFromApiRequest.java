package com.vin.dto;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "GetUserFromApiRequest")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
public class GetUserFromApiRequest {
    private Long id;
}