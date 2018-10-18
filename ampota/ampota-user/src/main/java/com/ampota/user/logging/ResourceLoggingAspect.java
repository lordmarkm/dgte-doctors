package com.ampota.user.logging;

import org.apache.commons.lang3.RandomStringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import xyz.quadx.shared.dto.TxnRequest;
import xyz.quadx.xpay.shared.audit.TransactionReferenceHolder;
import xyz.xpay.shared.util.DateUtil;

@Aspect
@Component
//@EnableBinding(AuditLogPublisher.class)
public class ResourceLoggingAspect {

    private static final Logger LOG = LoggerFactory.getLogger("User resource");

    @Autowired
    private Gson gson;
//    @Autowired
//    private AuditLogPublisher auditLogPublisher;
    @Value("${spring.application.name}")
    private String appName;

    @Pointcut("execution(* com.ampota.user.resource..*(..))")
    public void allResourceMethods() {
    }

    @Around("allResourceMethods()")
    public Object logNonMobileResourceMethods(ProceedingJoinPoint pjp) throws Throwable {
        long startMs = System.currentTimeMillis();
        Object retval = null;
        String error = "none";
        try {
            retval = pjp.proceed();
        } catch (Exception e) {
            error = e.toString();
            throw e;
        } finally {
            long endMs = System.currentTimeMillis();
            long processingTimeMs = endMs - startMs;
            String type = pjp.getSignature().getDeclaringTypeName();
            String method = pjp.getSignature().getName();
            LOG.info("{}::{}::{} ms, args={}, retval={}, error={}", type, method, processingTimeMs, pjp.getArgs(), retval, error);
        }
        return retval;
    }


    private void handleTransactionReference(ProceedingJoinPoint pjp) {
        if (null == TransactionReferenceHolder.get()) {
            TransactionReferenceHolder.set(DateTime.now().toString(DateUtil.DATE_FORMAT)
                    + "-" + RandomStringUtils.randomAlphanumeric(5).toUpperCase());
        }

        if (null != pjp.getArgs() && pjp.getArgs().length > 0) {
            for (Object arg : pjp.getArgs()) {
                if (null == arg) {
                    continue;
                }
                if (TxnRequest.class.isAssignableFrom(arg.getClass()) && null != ((TxnRequest) arg).getTxnReference()) {
                    ((TxnRequest) arg).setTxnReference(TransactionReferenceHolder.get());
                }
            }
        }
    }

}
