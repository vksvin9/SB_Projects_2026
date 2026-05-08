package com.vin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name required")
    private String name;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank(message = "Password required")
    private String password;

    private String role;

    @Column(length = 1000)
    private String bio;

    @OneToMany(mappedBy = "user")
    private List<Post> posts =
            new ArrayList<>();
}