package com.vin.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

@Slf4j
@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* com.vin..*(..))")
    public Object log(ProceedingJoinPoint jp) throws Throwable {

        long start = System.currentTimeMillis();

        // IN LOG
        log.info("IN -> {} args={}",
                jp.getSignature(),
                Arrays.toString(jp.getArgs()));

        try {
            Object result = jp.proceed();

            long time = System.currentTimeMillis() - start;

            // SAFE OUT LOG
            if (result instanceof Collection<?> collection) {
                log.info("OUT -> {} size={} time={}ms",
                        jp.getSignature(),
                        collection.size(),
                        time);
            } else {
                log.info("OUT -> {} result={} time={}ms",
                        jp.getSignature(),
                        result,
                        time);
            }

            return result;

        } catch (Exception ex) {

            log.error("EXCEPTION -> {} msg={}",
                    jp.getSignature(),
                    ex.getMessage(),
                    ex);

            throw ex;
        }
    }
}