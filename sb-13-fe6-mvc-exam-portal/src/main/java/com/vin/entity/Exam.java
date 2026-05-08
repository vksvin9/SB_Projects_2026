package com.vin.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "exams")
public class Exam extends BaseEntity {

    @Column(nullable = false)
    private String title;

    private String description;

    private Integer durationMinutes;

    private Integer totalMarks;

    private LocalDateTime startTime;

    private LocalDateTime endTime;

    @OneToMany(mappedBy = "exam",
            cascade = CascadeType.ALL)
    private List<Question> questions;
}