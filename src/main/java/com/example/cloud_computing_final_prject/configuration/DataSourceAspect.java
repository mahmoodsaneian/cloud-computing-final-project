package com.example.cloud_computing_final_prject.configuration;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(org.springframework.transaction.annotation.Transactional) && execution(* com.example..*.*(..))")
    public void setDataSource(JoinPoint joinPoint) {
        if (joinPoint.getSignature().getName().startsWith("find") ||
                joinPoint.getSignature().getName().startsWith("get")) {
            DataSourceRouting.setDataSource("SLAVE");
        } else {
            DataSourceRouting.setDataSource("MASTER");
        }
    }

    @After("@annotation(org.springframework.transaction.annotation.Transactional)")
    public void clearDataSource() {
        DataSourceRouting.clear();
    }
}
