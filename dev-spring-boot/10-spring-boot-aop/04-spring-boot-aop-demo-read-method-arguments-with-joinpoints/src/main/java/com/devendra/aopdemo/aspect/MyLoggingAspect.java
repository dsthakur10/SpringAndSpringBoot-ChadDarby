package com.devendra.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {
    @Before("AOPExpressions.forDAOPackageNoGetterSetter()")
    public void beforeAddAccount(JoinPoint theJoinPoint) {
        System.out.println("\n -----> Executing @Before Advice for given package on ANY methods with ANY return type with any #parameters");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();

        System.out.println("Method: " + methodSignature);

        // display the method arguments
        Object[] args = theJoinPoint.getArgs();

        for(Object tempArg: args)
            System.out.println(tempArg);
    }
}

