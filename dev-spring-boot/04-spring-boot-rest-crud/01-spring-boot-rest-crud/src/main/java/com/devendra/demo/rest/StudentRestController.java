package com.devendra.demo.rest;

import com.devendra.demo.entity.Student;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    // define endpoint for "/students" --> return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {

        List<Student> theStudents = new ArrayList<>();

        theStudents.add(new Student("Devendra", "Thakur"));
        theStudents.add(new Student("Meghna", "Dubey"));
        theStudents.add(new Student("Shree", "Ram"));
        theStudents.add(new Student("Sita", "Maiya"));

        return theStudents;
    }
}
