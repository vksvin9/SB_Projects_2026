package com.vin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("""
            execution(* com.vin.examportal.controller..*(..)) ||
            execution(* com.vin.examportal.service..*(..)) ||
            execution(* com.vin.examportal.repository..*(..))
            """)
    public Object logAround(ProceedingJoinPoint joinPoint)
            throws Throwable {

        String className =
                joinPoint.getSignature().getDeclaringTypeName();

        String methodName =
                joinPoint.getSignature().getName();

        log.info("ENTER -> {}.{}", className, methodName);

        Object result = joinPoint.proceed();

        log.info("EXIT -> {}.{}", className, methodName);

        return result;
    }
}