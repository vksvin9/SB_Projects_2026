package com.vin.aop;

import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    private static final Logger log = LoggerFactory.getLogger(LoggingAspect.class);

    // Controller + Service
    @Around("execution(* com.vin..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        long start = System.currentTimeMillis();

        String method = joinPoint.getSignature().toShortString();
        Object[] args = joinPoint.getArgs();

        log.info("IN  → {} args={}", method, args);

        try {
            Object result = joinPoint.proceed();

            long time = System.currentTimeMillis() - start;

            log.info("OUT → {} result={} time={}ms", method, result, time);

            return result;

        } catch (Exception ex) {
            log.error("EX  → {} error={}", method, ex.getMessage(), ex);
            throw ex;
        }
    }
}