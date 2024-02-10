package com.devendra.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {

    // This is where we add all our related advices for logging

    // Pointcut for DAO package
    @Pointcut("execution(public * com.devendra.aopdemo.dao.*.*(..))")
    private void forDAOPackage() {}

    // Pointcut for getter methods
    @Pointcut("execution(public * com.devendra.aopdemo.dao.*.get*(..))")
    private void getter() {}

    // Pointcut for setter methods
    @Pointcut("execution(public * com.devendra.aopdemo.dao.*.set*(..))")
    private void setter() {}

    // Pointcut to include package & exclude all getters/setters
    @Pointcut("forDAOPackage() && !(getter() || setter())")
    private void forDAOPackageNoGetterSetter() {}

    @Before("forDAOPackageNoGetterSetter()")
    public void beforeAnyMethodsInsideGivenPackageAdvice() {

        // Write custom code to execute before the given method runs (method in pointcut expression)

        System.out.println("\n -----> Executing @Before Advice for given package on ANY methods with ANY return type with any #parameters");
    }

    @Before("forDAOPackageNoGetterSetter()")
    public void performAPIAnalytics() {
        System.out.println("\n -----> Performing API analytics");
    }
}

