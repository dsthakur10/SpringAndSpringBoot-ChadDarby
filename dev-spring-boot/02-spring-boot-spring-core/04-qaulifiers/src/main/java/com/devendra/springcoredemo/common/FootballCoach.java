package com.devendra.springcoredemo.common;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice dribbling & shooting for 1 hour each !!!";
    }
}
