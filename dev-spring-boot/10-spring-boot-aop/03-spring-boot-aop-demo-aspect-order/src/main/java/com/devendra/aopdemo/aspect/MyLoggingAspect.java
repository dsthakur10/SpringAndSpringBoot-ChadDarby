package com.devendra.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {
    @Before("AOPExpressions.forDAOPackageNoGetterSetter()")
    public void beforeAddAccount() {
        System.out.println("\n -----> Executing @Before Advice for given package on ANY methods with ANY return type with any #parameters");
    }

}

