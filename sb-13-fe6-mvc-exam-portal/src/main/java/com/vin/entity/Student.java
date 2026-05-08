package com.vin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "students")
public class Student extends BaseEntity {

    @Column(nullable = false)
    private String fullName;

    @Column(unique = true)
    private String mobile;

    private String address;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}