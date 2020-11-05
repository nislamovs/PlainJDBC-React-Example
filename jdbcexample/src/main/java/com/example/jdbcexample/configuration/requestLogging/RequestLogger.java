package com.example.jdbcexample.configuration.requestLogging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
@Slf4j
public class RequestLogger {

    @Before("execution(* com.example.jdbcexample.controllers.*.*(..))")
    public void before(JoinPoint joinPoint) {
        log.info("request made:" + joinPoint.getSignature());
        for (Object object : joinPoint.getArgs()) {
            log.info("with argument/body: " + object );
        }
    }
}

