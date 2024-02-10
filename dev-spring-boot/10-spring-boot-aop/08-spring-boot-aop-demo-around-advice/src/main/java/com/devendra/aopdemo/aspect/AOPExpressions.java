package com.devendra.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class AOPExpressions {
    @Pointcut("execution(public * com.devendra.aopdemo.dao.*.*(..))")
    public void forDAOPackage() {}

    // Pointcut for getter methods
    @Pointcut("execution(public * com.devendra.aopdemo.dao.*.get*(..))")
    public void getter() {}

    // Pointcut for setter methods
    @Pointcut("execution(public * com.devendra.aopdemo.dao.*.set*(..))")
    public void setter() {}

    // Pointcut to include package & exclude all getters/setters
    @Pointcut("forDAOPackage() && !(getter() || setter())")
    public static void forDAOPackageNoGetterSetter() {}

}
