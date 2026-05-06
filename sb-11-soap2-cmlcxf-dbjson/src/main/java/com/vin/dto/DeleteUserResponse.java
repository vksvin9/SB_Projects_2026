package com.vin.dto;

import jakarta.xml.bind.annotation.*;
import lombok.Data;

@XmlRootElement(name = "DeleteUserResponse")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name="DeleteUserResponseType")
@Data
public class DeleteUserResponse {

    private String status;
}