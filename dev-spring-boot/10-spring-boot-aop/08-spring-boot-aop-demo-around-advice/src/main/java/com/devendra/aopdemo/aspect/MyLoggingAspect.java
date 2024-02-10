package com.devendra.aopdemo.aspect;

import com.devendra.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component
@Order(2)
public class MyLoggingAspect {

//    @Before("AOPExpressions.forDAOPackageNoGetterSetter()")
//    public void beforeAddAccount(JoinPoint theJoinPoint) {
//        System.out.println("\n -----> Executing @Before Advice for given package on ANY methods with ANY return type with any #parameters");
//
//        // display the method signature
//        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
//
//        System.out.println("Method: " + methodSignature);
//
//        // display the method arguments
//        Object[] args = theJoinPoint.getArgs();
//
//        for(Object tempArg: args)
//            System.out.println(tempArg);
//    }


    // modifier is optional
//    @AfterReturning(
//            pointcut = "execution(* findAccounts(..))",
//            returning = "result"
//    )
//    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
//
//        // print out which method we are advising on
//        String method = theJoinPoint.getSignature().toShortString();
//        System.out.println("\n-----> Executing @AfterReturning advice on method: " + method);
//
//        // print out the results of method call before MODIFY
//        System.out.println("-----> Result before Post-processing data: " + result);
//
//        // Let us post-process the data --> Modify the return value
//        convertAccountNamesToUpperCase(result);
//    }

//    private void convertAccountNamesToUpperCase(List<Account> result) {
//
//        for(Account acc: result) {
//            String name = acc.getName().toUpperCase();
//            acc.setName(name);
//        }
//    }
//
//    @AfterThrowing(
//            pointcut = "execution(* findAccounts(..))",
//            throwing = "exc"
//    )
//    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable exc) {
//
//        // print out which method we are advising on
//        String method = theJoinPoint.getSignature().toShortString();
//        System.out.println("\n-----> Executing @AfterThrowing advice on method: " + method);
//
//        // log the exception
//        System.out.println("\n-----> Exception is " + exc);
//
//    }

//    @After("execution(* findAccounts(..))")
//    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
//        // print out which method we are advising on
//        String method = theJoinPoint.getSignature().toShortString();
//        System.out.println("\n-----> Executing @After (finally) advice on method: " + method);
//    }

//    @Around("execution(* getFortune(..))")
//    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
//
//        // print out method we are advising on
//        String method = theProceedingJoinPoint.getSignature().toShortString();
//        System.out.println("\n-----> Executing @Around advice on method: " + method);
//
//        // get begin timestamp
//        long begin = System.currentTimeMillis();
//
//        // now, let's execute the method --> proceed() will call target method & the return type of proceed is Object
//        Object result = theProceedingJoinPoint.proceed();
//
//        // get end timestamp
//        long end = System.currentTimeMillis();
//
//        // compute duration & display
//        long duration = end - begin;
//        System.out.println("\n-------> Duration: " + duration / 1000.0 + " seconds");
//
//        return result;
//    }


    // Handle Exception using Around advice --> Main APP does not know about the exception
    // Exception is handled in @around advice
    @Around("execution(* getFortune(..))")
    public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {

        // print out method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n-----> Executing @Around advice on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method --> proceed() will call target method & the return type of proceed is Object
        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception e) {
            System.out.println(e.getMessage());

            result = "Major Accident! But no worries. Your private JET is on its way";
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration & display
        long duration = end - begin;
        System.out.println("\n-------> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

}
