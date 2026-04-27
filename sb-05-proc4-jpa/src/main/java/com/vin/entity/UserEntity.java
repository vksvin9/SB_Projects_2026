package com.vin.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users_identity", schema = "user_db")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private Long id;

    private String name;
    private String email;
}