package com.devendra.aopdemo.aspect;

// import com.devendra.aopdemo.aspect.AOPExpressions;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyAPIAnalyticsAspect {
    @Before("AOPExpressions.forDAOPackageNoGetterSetter()")
    public void performAPIAnalytics() {
        System.out.println("\n -----> Performing API analytics");
    }

}
