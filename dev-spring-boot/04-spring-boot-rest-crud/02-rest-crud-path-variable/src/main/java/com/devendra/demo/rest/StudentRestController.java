package com.devendra.demo.rest;

import com.devendra.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define endpoint to load a student data ONLY ONCE
    // @PostConstruct act as init() method for our RestController
    @PostConstruct
    public void loadData() {
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Devendra", "Thakur"));
        theStudents.add(new Student("Meghna", "Dubey"));
        theStudents.add(new Student("Shree", "Ram"));
        theStudents.add(new Student("Sita", "Maiya"));
    }

    // define endpoint for "/students" --> return a list of students
    @GetMapping("/students")
    public List<Student> getStudents() {
        return theStudents;
    }

    // define endpoint "/student/{studentId}" - return student at index. This index is Arraylist's index
    // theStudents.get(id) will return an element at index "id" from the Arraylist
    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {
        return theStudents.get(studentId);
    }
}
