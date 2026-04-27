package com.vin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

//CREATE DUMMY ENTITY (MANDATORY)
//Hibernate requires at least one entity.
@Entity
@Table(name = "users_identity", schema = "user_db")
public class UserEntity {

    @Id
    private Long id;

    private String name;
    private String email;
}
