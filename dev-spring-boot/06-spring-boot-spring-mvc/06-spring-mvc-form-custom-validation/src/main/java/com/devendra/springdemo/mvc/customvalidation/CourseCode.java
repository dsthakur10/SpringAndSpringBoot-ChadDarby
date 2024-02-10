package com.devendra.springdemo.mvc.customvalidation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// @Constraint is used to specify the ConstraintValidator class which has the business rules to validate our custom annotation.
// @Target is used to know where can we apply the custom validation annotation (on a method or on a field or somewhere else)
// @Retention is used to specify how long this custom annotation will be retained --> Here it is till runtime
@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CourseCode {

    // define default course code
    public String value() default "CSE";

    // define default error message
    public String message() default "must start with CSE";

    // define default groups
    public Class<?>[] groups() default {};

    // define default payloads
    public Class<? extends Payload>[] payload() default {};
}
