package com.devendra.springcoredemo.RESTContoller;

import com.devendra.springcoredemo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach myCoach;
    private Coach anotherCoach;

    // define a constructor for dependency injection
    @Autowired
    public DemoController(
            @Qualifier("footballCoach") Coach theCoach,
            @Qualifier("footballCoach") Coach theAnotherCoach) {
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }

    @GetMapping("/dailyworkout")
    public String getDailyWorkout() {
        return myCoach.getDailyWorkout();
    }

    @GetMapping("/checkScope")
    public String checkScope() {
        return "Comparing beans: mycoach == anotherCoach ? " + (myCoach == anotherCoach);
    }
}
