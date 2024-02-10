package com.devendra.advancedmappingdemo.dao;

import com.devendra.advancedmappingdemo.entity.Course;
import com.devendra.advancedmappingdemo.entity.Instructor;
import com.devendra.advancedmappingdemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    private EntityManager entityManager;

    // Inject EntityManager using Constructor Injection
    @Autowired
    public AppDAOImpl(EntityManager theEntityManager) {
        entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {
        entityManager.persist(theInstructor);
    }

    @Override
    public Instructor findInstructorById(int theId) {
        return entityManager.find(Instructor.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorById(int theId) {
        Instructor instructor = entityManager.find(Instructor.class, theId);

        // get the courses
        List<Course> courses = instructor.getCourses();

        // break the association of all courses for the instructor
        for(Course tempCourse: courses)
            tempCourse.setInstructor(null);

        // delete the instructor
        entityManager.remove(instructor);
    }


    // Bi-Directional setup
    @Override
    public InstructorDetail findInstructorDetailsById(int theId) {
        return entityManager.find(InstructorDetail.class, theId);
    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(int theId) {

        // retrieve instructor_detail
        InstructorDetail instructorDetail = findInstructorDetailsById(theId);

        // delete
        entityManager.remove(instructorDetail);
    }


    // Deleting only one-side entity without affecting the other side
    @Override
    @Transactional
    public void deleteInstructorDetailByIdOneWay(int theId) {

        // retrieve instructor_detail
        InstructorDetail instructorDetail = findInstructorDetailsById(theId);

        // break the mapping link from Instructor to Instructor-Detail so that Instructor can exist alone
        instructorDetail.getInstructor().setInstructorDetail(null);

        // delete
        entityManager.remove(instructorDetail);
    }

    @Override
    public List<Course> findCoursesByInstructorId(int theId) {

        // create a query
        TypedQuery<Course> query = entityManager.createQuery(
                "from Course where instructor.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        List<Course> courses = query.getResultList();

        return courses;
    }

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

        // create query
        TypedQuery<Instructor> query = entityManager.createQuery(
                "select I from Instructor I join fetch I.courses where I.id = :data", Instructor.class);
        query.setParameter("data", theId);

        // Execute query
        Instructor instructor = query.getSingleResult();

        return instructor;
    }

    @Override
    public Course findCourseById(int theId) {
        return entityManager.find(Course.class, theId);
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {
        entityManager.merge(tempInstructor);
    }

    @Override
    @Transactional
    public void update(Course tempCourse) {
        entityManager.merge(tempCourse);
    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        // retrieve the course
        Course course = entityManager.find(Course.class, theId);

        // delete it simply
        entityManager.remove(course);
    }

    @Override
    @Transactional
    public void save(Course theCourse) {
        entityManager.persist(theCourse);
    }

    @Override
    public Course findCourseAndReviewsByCourseId(int theId) {

        // create Query
        TypedQuery<Course> query = entityManager.createQuery(
                "select C from Course C JOIN FETCH C.reviews where C.id = :data", Course.class);
        query.setParameter("data", theId);

        // execute query
        Course course = query.getSingleResult();
        return course;
    }
}
