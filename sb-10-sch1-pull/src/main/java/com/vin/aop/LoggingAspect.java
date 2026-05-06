package com.vin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.vin..*(..))")
    public Object log(ProceedingJoinPoint pjp) throws Throwable {

        long start = System.currentTimeMillis();

        log.info("IN -> {} args={}", pjp.getSignature(), pjp.getArgs());

        try {
            Object result = pjp.proceed();
            log.info("OUT -> {} result={}", pjp.getSignature(), result);
            return result;
        } catch (Exception ex) {
            log.error("EX -> {} error={}", pjp.getSignature(), ex.getMessage());
            throw ex;
        } finally {
            log.info("TIME -> {} ms", System.currentTimeMillis() - start);
        }
    }
}