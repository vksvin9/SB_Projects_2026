package com.vin.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.*;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Pointcut("execution(* com.vin.service..*(..))")
    public void serviceLayer() {}

    @Around("serviceLayer()")
    public Object log(ProceedingJoinPoint jp) throws Throwable {

        log.info("IN -> {} {}", jp.getSignature(), jp.getArgs());

        try {
            Object res = jp.proceed();
            log.info("OUT -> {} {}", jp.getSignature(), res);
            return res;
        } catch (Exception ex) {
            log.error("ERR -> {} {}", jp.getSignature(), ex.getMessage());
            throw ex;
        }
    }
}