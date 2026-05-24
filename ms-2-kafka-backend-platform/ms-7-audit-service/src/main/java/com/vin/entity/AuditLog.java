package com.vin.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "MS7_AUDIT_LOGS")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String eventType;

    @Column(nullable = false)
    private String entityId;

    @Column(nullable = false)
    private String entityName;

    @Column(nullable = false)
    private String entityEmail;

    @Column(nullable = false)
    private LocalDateTime createdAt;
}