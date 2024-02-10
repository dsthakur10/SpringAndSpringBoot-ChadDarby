package com.devendra.springdemo.mvc;

import jakarta.validation.constraints.*;

public class Customer {
    private String firstName;

    @NotNull(message = "is required field")
    @Size(min=1, message = "is required field")
    private String lastName;

    // Using Wrapper class instead of primitive to avoid String -> Int conversion error
    @NotNull(message = "Required field")
    @Min(value=0, message = "must be >= 0")
    @Max(value=10, message = "must be <= 10")
    private Integer freePasses;

    @NotNull(message = "Postal code is mandatory")
    @Pattern(regexp="^[a-zA-Z0-9]{6}", message="only 6 chars/digits")
    private String postalCode;

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

    public Integer getFreePasses() {
        return freePasses;
    }

    public void setFreePasses(Integer freePasses) {
        this.freePasses = freePasses;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
}
