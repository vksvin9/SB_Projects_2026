package com.vin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class LoggingAspect {

    @Around("execution(* com.vin.controller..*(..)) || " +
            "execution(* com.vin.service..*(..)) || " +
            "execution(* com.vin.repository..*(..))")
    public Object logAround(ProceedingJoinPoint joinPoint)
            throws Throwable {

        long start = System.currentTimeMillis();

        String methodName =
                joinPoint.getSignature().toShortString();

        log.info("METHOD IN : {}", methodName);

        log.info("ARGS : {}",
                Arrays.toString(joinPoint.getArgs()));

        try {

            Object result = joinPoint.proceed();

            long executionTime =
                    System.currentTimeMillis() - start;

            log.info("METHOD OUT : {}", methodName);

            log.info("RESPONSE : {}", result);

            log.info("TIME TAKEN : {} ms", executionTime);

            return result;

        } catch (Exception ex) {

            log.error("EXCEPTION IN : {}", methodName);

            log.error("ERROR : {}", ex.getMessage());

            throw ex;
        }
    }

    @Before("execution(* com.vin.service..*(..))")
    public void beforeService() {
        System.out.println("==== SERVICE START ====");
    }

    @After("execution(* com.vin.service..*(..))")
    public void afterService() {
        System.out.println("==== SERVICE END ====");
    }
}