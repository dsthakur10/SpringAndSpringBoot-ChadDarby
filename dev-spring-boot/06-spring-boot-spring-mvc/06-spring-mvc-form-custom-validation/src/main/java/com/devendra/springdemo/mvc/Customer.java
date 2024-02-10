package com.devendra.springdemo.mvc;

import com.devendra.springdemo.mvc.customvalidation.CourseCode;
import jakarta.validation.constraints.*;

public class Customer {
    @NotNull(message = "is required field")
    @Size(min=1, message = "is required field")
    private String firstName;

    @NotNull(message = "is required field")
    @Size(min=1, message = "is required field")
    private String lastName;

    @CourseCode(value="MTECH", message="must start with MTECH")
    private String courseCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }
}
