package com.devendra.springboot.demo.mycoolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

    // Injecting properties from application.properties
    @Value("${coach.name}")
    private String coachName;
    @Value("${team.name}")
    private String teamName;

    // expose "/" that return "Hello World"
    @GetMapping("/")
    public String sayHello() {
        return "Hello Devendra. You will conquer the world";
    }

    // expose a new end-point for "workout"
    @GetMapping("/workout")
    public String getDailyWorkout() {
        return "No matter what, you should always workout";
    }

    // expose a new end-point for "pain"
    @GetMapping("/pain")
    public String getMotivation() {
        return "Learn PAIN, embrace the PAIN";
    }

    @GetMapping("/worldcup")
    public String getWorldCupWinningTeam()
    {
        return "World cup winning team: " + teamName + " | World cup winning team coach: " + coachName;
    }
}
