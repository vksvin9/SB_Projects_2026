package com.vin.model;

import lombok.Data;

@Data
public class Employee {
    private Long id;
    private String name;
    private String dept;
    private Double salary;
}