package com.devendra.advancedmappingdemo.dao;

import com.devendra.advancedmappingdemo.entity.Instructor;
import com.devendra.advancedmappingdemo.entity.InstructorDetail;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
}
