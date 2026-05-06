package com.vin.model;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppDataResponse {
    private Long id;
    private String reqId;
    private String name;
    private String type;
    private String status;
    private Integer retryCount;
    private LocalDateTime createdAt;
}