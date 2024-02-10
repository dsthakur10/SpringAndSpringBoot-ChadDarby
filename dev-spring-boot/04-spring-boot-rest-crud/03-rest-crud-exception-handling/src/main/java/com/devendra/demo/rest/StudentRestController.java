package com.devendra.demo.rest;

import com.devendra.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    // define endpoint to load a student data ONLY ONCE
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

    // define endpoint "/student/{studentId} - return student at index"

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        // validate the studentID
        if(studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student not found for given student ID: " + studentId);
        }

        return theStudents.get(studentId);
    }


    // Add an exception handler
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exc) {

        // create a StudentErrorResponse
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage((exc.getMessage()));
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity --> the form of response entity --> <body, status-code>
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

        // 2nd parameter is to send description of status in the response (NOT for the response body)
    }


    // Add generic exception to handle all cases
    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception exc) {

        // create a StudentErrorResponse
        StudentErrorResponse errorResponse = new StudentErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage((exc.getMessage()));
        errorResponse.setTimeStamp(System.currentTimeMillis());

        // return ResponseEntity --> the form of response entity --> <body, status-code>
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

        // 2nd parameter is to send description of status in the response (NOT for the response body)
    }
}
