package com.vin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.vin.service..*(..)) || execution(* com.vin.controller..*(..))")
    public void appFlow() {}

    @Around("appFlow()")
    public Object log(ProceedingJoinPoint joinPoint) throws Throwable {

        String method = joinPoint.getSignature().toShortString();

        log.info("IN -> {} args={}", method, joinPoint.getArgs());

        try {
            Object result = joinPoint.proceed();
            log.info("OUT -> {} result={}", method, result);
            return result;
        } catch (Exception ex) {
            log.error("EXCEPTION -> {} error={}", method, ex.getMessage());
            throw ex;
        }
    }
}