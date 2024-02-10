package com.devendra.advancedmappingdemo.dao;

import com.devendra.advancedmappingdemo.entity.Instructor;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);
}
