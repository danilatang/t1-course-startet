package com.example.aspect;

import com.example.properties.HttpLoggingProperties;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class HttpLoggingAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final HttpLoggingProperties httpLoggingProperties;

    public HttpLoggingAspect(HttpLoggingProperties httpLoggingProperties) {

        this.httpLoggingProperties = httpLoggingProperties;
    }


    @Before("@annotation(com.example.annotation.HttpLogging)")
    public void logBefore(JoinPoint joinPoint) {
        log("Выполнение метода: {}", joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "@annotation(com.example.annotation.HttpLogging)", returning = "result")
    public void logAfterReturning(Object result) {
        log("Метод выполнен успешно, результат: {}", result);
    }

    @AfterThrowing(pointcut = "@annotation(com.example.annotation.HttpLogging)", throwing = "exception")
    public void logAfterThrowing(JoinPoint joinPoint, Exception exception) {
        log("Метод {} выбросил исключение: {}", joinPoint.getSignature().getName(), exception.getMessage());
    }

    @Around("@annotation(com.example.annotation.HttpLogging)")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = joinPoint.proceed();

        long endTime = System.currentTimeMillis() - startTime;
        log("Метод {} выполнялся {} мс", joinPoint.getSignature(), endTime);
        return result;
    }

    private void log(String message, Object... args) {
        switch (httpLoggingProperties.getLevel()) {
            case DEBUG -> logger.debug(message);
            case WARN-> logger.warn(message);
            case ERROR-> logger.error(message);
            default-> logger.info(message);
        }
    }
}
