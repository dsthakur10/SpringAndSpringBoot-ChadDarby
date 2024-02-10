package com.devendra.advancedmappingdemo.dao;

import com.devendra.advancedmappingdemo.entity.Course;
import com.devendra.advancedmappingdemo.entity.Instructor;
import com.devendra.advancedmappingdemo.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {
    void save(Instructor theInstructor);

    Instructor findInstructorById(int theId);

    void deleteInstructorById(int theId);

    InstructorDetail findInstructorDetailsById(int theId);

    void deleteInstructorDetailById(int theId);

    void deleteInstructorDetailByIdOneWay(int theId);

    List<Course> findCoursesByInstructorId(int theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    Course findCourseById(int theId);

    void update(Instructor tempInstructor);

    void update(Course tempCourse);

    void deleteCourseById(int theId);

    void save(Course theCourse);

    Course findCourseAndReviewsByCourseId(int theId);

}
