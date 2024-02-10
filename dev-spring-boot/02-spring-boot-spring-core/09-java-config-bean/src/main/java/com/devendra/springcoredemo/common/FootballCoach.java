package com.devendra.springcoredemo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
    public FootballCoach() {
        System.out.println("Inside constructor: " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Practice dribbling & shooting for 1 hour each !!!";
    }
}
