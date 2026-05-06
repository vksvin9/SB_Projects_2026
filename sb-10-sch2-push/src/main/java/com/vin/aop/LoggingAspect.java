package com.vin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.vin..*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {

        log.info("IN -> {}", pjp.getSignature());

        Object out = pjp.proceed();

        log.info("OUT -> {}", pjp.getSignature());

        return out;
    }
}