package com.devendra.springdemo.mvc.customvalidation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

// Actual Custom Constraint Validator class where business logic/rule is written for customisable behavior.
public class CourseCodeConstraintValidator implements ConstraintValidator<CourseCode, String> {

    private String coursePrefix;
    @Override
    public void initialize(CourseCode theCourseCode) {
        // .value() actually accesses the attribute value for the given annotation
        coursePrefix = theCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {

        // theCode value is the HTML form data entered by user
        // constraintValidatorContext --> you can place additional error messages here
        boolean result;
        if(theCode != null) {
            result = theCode.startsWith(coursePrefix);
        } else {
            result = false;
        }
        return result;
    }
}
