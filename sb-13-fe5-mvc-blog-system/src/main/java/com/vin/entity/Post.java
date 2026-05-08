package com.vin.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title required")
    private String title;

    @Column(length = 10000)
    @NotBlank(message = "Content required")
    private String content;

    @Column(unique = true)
    private String slug;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(
            mappedBy = "post",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Comment> comments =
            new ArrayList<>();

    @ManyToMany
    @JoinTable(
            name = "post_tags",
            joinColumns =
            @JoinColumn(name = "post_id"),
            inverseJoinColumns =
            @JoinColumn(name = "tag_id")
    )
    private List<Tag> tags =
            new ArrayList<>();
}