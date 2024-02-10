package com.devendra.aopdemo.aspect;

import com.devendra.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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
    @AfterReturning(
            pointcut = "execution(* findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {

        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n-----> Executing @AfterReturning advice on method: " + method);

        // print out the results of method call before MODIFY
        System.out.println("-----> Result before Post-processing data: " + result);

        // Let us post-process the data --> Modify the return value
        convertAccountNamesToUpperCase(result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {

        for(Account acc: result) {
            String name = acc.getName().toUpperCase();
            acc.setName(name);
        }
    }
}
