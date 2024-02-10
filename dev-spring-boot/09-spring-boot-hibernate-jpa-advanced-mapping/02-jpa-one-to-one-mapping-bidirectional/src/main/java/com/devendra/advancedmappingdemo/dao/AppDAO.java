package com.devendra.advancedmappingdemo.dao;

import com.devendra.advancedmappingdemo.entity.Instructor;
import com.devendra.advancedmappingdemo.entity.InstructorDetail;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailsById(int theId);

    void deleteInstructorDetailById(int theId);

    void deleteInstructorDetailByIdOneWay(int theId);
}
