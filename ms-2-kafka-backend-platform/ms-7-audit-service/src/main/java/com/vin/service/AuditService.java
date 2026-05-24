package com.vin.service;

import com.vin.entity.AuditLog;
import com.vin.event.UserCreatedEvent;
import com.vin.repository.AuditLogRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuditService {

    private final AuditLogRepository auditLogRepository;

    public void saveAuditLog(
            UserCreatedEvent event) {

        AuditLog auditLog =
                AuditLog.builder()
                        .eventType("USER_CREATED")
                        .entityId(String.valueOf(event.getId()))
                        .entityName(event.getName())
                        .entityEmail(event.getEmail())
                        .createdAt(LocalDateTime.now())
                        .build();

        auditLogRepository.save(auditLog);

        log.info(
                "Audit log saved for email={}",
                event.getEmail()
        );
    }
}