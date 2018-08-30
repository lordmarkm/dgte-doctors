package com.dgtedr.project.gateway.logging;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ResourceLoggingAspect {

    private static final Logger LOG = LoggerFactory.getLogger("Mobile gateway resource");

    @Pointcut("execution(* com.dgtedr.project.gateway.resource..*(..))")
    public void allResourceMethods() {
    }

    @Around("allResourceMethods()")
    public Object logResourceMethodInvocation(ProceedingJoinPoint pjp) throws Throwable {
        long startMs = System.currentTimeMillis();
        Object retval = pjp.proceed();
        long endMs = System.currentTimeMillis();
        long processingTimeMs = endMs - startMs;
        String type = pjp.getSignature().getDeclaringTypeName();
        String method = pjp.getSignature().getName();
        LOG.info("{}::{}::{} ms, args={}, retval={}", type, method, processingTimeMs, pjp.getArgs(), retval);

        return retval;
    }

}
