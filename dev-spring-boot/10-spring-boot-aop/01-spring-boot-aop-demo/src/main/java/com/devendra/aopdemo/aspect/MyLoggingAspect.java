package com.devendra.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class MyLoggingAspect {

    // This is where we add all our related advices for logging

    // Let's start with @Before advice

    // pointcut expression --> execution(public void addAccount())
    // Can give any method name after advice in place of beforeAddAccountAdvice()

//    // 1. Matches any method-name = "addAccount"
//    @Before("execution(public void addAccount())")
//    public void beforeAddAccountAdvice() {
//
//        // Write custom code to execute before the given method runs (method in pointcut expression)
//
//        System.out.println("\n -----> Executing @Before Advice on addAccount()");
//    }


//    // 2. Matches only method-name = "addAccount" from AccountDAO class
//    @Before("execution(public void com.devendra.aopdemo.dao.AccountDAO.addAccount())")
//    public void beforeAddAccountForAccountDAOAdvice() {
//
//        // Write custom code to execute before the given method runs (method in pointcut expression)
//
//        System.out.println("\n -----> Executing @Before Advice on ACCOUNTDAO class addAccount()");
//    }


//    // 3. Matches any method-name which starts with "add"
//    @Before("execution(public void add*())")
//    public void beforeAnyStartsWithAddAdvice() {
//
//        // Write custom code to execute before the given method runs (method in pointcut expression)
//
//        System.out.println("\n -----> Executing @Before Advice on ANY add()");
//    }

//    // 4. Matches on any return type with ANY method name that starts with "add"
//    @Before("execution(public * add*())")
//    public void beforeAnyReturnAnyStartsWithAddAdvice() {
//
//        // Write custom code to execute before the given method runs (method in pointcut expression)
//
//        System.out.println("\n -----> Executing @Before Advice on ANY add() with ANY return type");
//    }

//    // 5. Matches on any return type with method = addAccount & parameters = Object Account
//    @Before("execution(public * addAccount(com.devendra.aopdemo.Account))")
//    public void beforeAddAccountAnyReturnAccountParamAdvice() {
//
//        // Write custom code to execute before the given method runs (method in pointcut expression)
//
//        System.out.println("\n -----> Executing @Before Advice on ANY addAccount() with ANY return type with parameter of type Account");
//    }

//    // 6. Matches on any return type with method = addAccount & Account parameter followed by ANY number of parameters --> ".." represents 0/more
//    @Before("execution(public * addAccount(com.devendra.aopdemo.Account, ..))")
//    public void beforeAddAccountAnyReturnAccountAnyParamAdvice() {
//
//        // Write custom code to execute before the given method runs (method in pointcut expression)
//
//        System.out.println("\n -----> Executing @Before Advice on ANY addAccount() with ANY return type with Account parameter followed by any #parameters");
//    }

//    // 7. Matches on any return type with method = addAccount & ANY number of parameters --> ".." represents 0/more
//    @Before("execution(public * addAccount(..))")
//    public void beforeAddAccountAnyReturnAnyParamAdvice() {
//
//        // Write custom code to execute before the given method runs (method in pointcut expression)
//
//        System.out.println("\n -----> Executing @Before Advice on ANY addAccount() with ANY return type with Account parameter followed by any #parameters");
//    }


    // 8. Matches on any method inside given package
    // 1st * --> Class inside given package
    // 2nd * --> Method inside given package
    // (..) --> Any parameters
    @Before("execution(public * com.devendra.aopdemo.dao.*.*(..))")
    public void beforeAnyMethodsInsideGivenPackageAdvice() {

        // Write custom code to execute before the given method runs (method in pointcut expression)

        System.out.println("\n -----> Executing @Before Advice for given package on ANY methods with ANY return type with any #parameters");
    }
}

