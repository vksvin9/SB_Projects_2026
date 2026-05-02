package com.vin.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Controller layer
    @Around("execution(* com.vin.controller..*(..))")
    public Object logController(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint, "CONTROLLER");
    }

    // Service layer
    @Around("execution(* com.vin.service..*(..))")
    public Object logService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint, "SERVICE");
    }

    // Repository layer
    @Around("execution(* com.vin.repository..*(..))")
    public Object logRepository(ProceedingJoinPoint joinPoint) throws Throwable {
        return logAround(joinPoint, "REPOSITORY(DB)");
    }

    private Object logAround(ProceedingJoinPoint joinPoint, String layer) throws Throwable {

        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("➡️ [{}] IN  : {} | args={}", layer, method, Arrays.toString(args));

        try {
            Object result = joinPoint.proceed();

            log.info("⬅️ [{}] OUT : {} | result={}", layer, method, result);

            return result;

        } catch (Exception ex) {

            log.error("❌ [{}] ERROR : {} | message={}", layer, method, ex.getMessage());

            throw ex;
        }
    }
}