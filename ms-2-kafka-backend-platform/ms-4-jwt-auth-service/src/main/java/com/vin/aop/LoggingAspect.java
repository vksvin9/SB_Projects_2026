package com.vin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Before("execution(* com.vin.service..*(..))")
    public void logMethodEntry(JoinPoint joinPoint) {
        log.debug(
                "Entering method: {} with arguments: {}",
                joinPoint.getSignature().toShortString(),
                Arrays.toString(joinPoint.getArgs())
        );
    }

    @AfterReturning(
            pointcut = "execution(* com.vin.service..*(..))",
            returning = "result"
    )
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        log.debug(
                "Exiting method: {} with result: {}",
                joinPoint.getSignature().toShortString(),
                result
        );
    }
}