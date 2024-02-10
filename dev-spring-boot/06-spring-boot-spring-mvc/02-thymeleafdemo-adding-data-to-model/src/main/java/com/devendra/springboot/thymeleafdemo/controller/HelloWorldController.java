package com.devendra.springboot.thymeleafdemo.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloWorldController {

    // need a controller method to show initial HTML form

    @RequestMapping("/showForm")
    public String showForm() {
        return "helloWorld-form";
    }

    // need a controller method to process the HTML form

    @RequestMapping("/processForm")
    public String processForm() {
        return "helloWorld-process";
    }

    // need a controller method to read form data & add data to model

    @RequestMapping("/processFormVersionTwo")
    public String addDataToModel(HttpServletRequest req, Model model) {

        // read request parameter from HTML form
        String name = req.getParameter("studentName");

        // convert data to UpperCase
        name = name.toUpperCase();

        // create the message
        String result = "Yo! " + name;

        // add data to the model
        model.addAttribute("message", result);

        return "helloWorld-process";
    }

    @RequestMapping("/processFormVersionThree")
    public String addDataToModelMVCBindingParams(@RequestParam("studentName") String theName, Model model) {

        // convert data to UpperCase
        theName = theName.toUpperCase();

        // create the message
        String result = "Hey Sports Maniac!! " + theName;

        // add data to the model
        model.addAttribute("message", result);

        return "helloWorld-process";
    }
}
