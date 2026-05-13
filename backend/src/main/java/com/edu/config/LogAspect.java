package com.edu.config;

import com.edu.common.Log;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
public class LogAspect {

    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);

    @Before("@annotation(log)")
    public void doBefore(JoinPoint joinPoint, Log log) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        
        logger.info(">>> New Request: {} {}", request.getMethod(), request.getRequestURL().toString());
        logger.info(">>> IP: {}", request.getRemoteAddr());
        logger.info(">>> Method: {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        logger.info(">>> Args: {}", Arrays.toString(joinPoint.getArgs()));
    }

    @AfterReturning(pointcut = "@annotation(log)", returning = "ret")
    public void doAfterReturning(Log log, Object ret) {
        logger.info("<<< Response: {}", ret);
    }
}