package com.vin.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "posts")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
}