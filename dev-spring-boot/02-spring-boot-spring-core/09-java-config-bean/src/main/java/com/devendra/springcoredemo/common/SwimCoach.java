package com.devendra.springcoredemo.common;

// SwimCoach did not have @Component. It failed the first time we ran it (without bean)
// Later, we configured it as a Spring bean with @Bean annotation
// It's working fine now.

public class SwimCoach implements Coach{
    public SwimCoach() {
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as a warm up !!!";
    }
}
