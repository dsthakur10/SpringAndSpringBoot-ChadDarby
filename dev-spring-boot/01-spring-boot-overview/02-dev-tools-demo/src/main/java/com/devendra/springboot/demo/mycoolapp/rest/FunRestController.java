package com.devendra.springboot.demo.mycoolapp.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FunRestController {

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
}
