package com.example.devendra.cruddemo.dao;

import com.example.devendra.cruddemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl implements EmployeeDAO{

    // define field for entityManager
    private final EntityManager entityManager;

    // setup constructor injection
    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Employee> findAll() {

        // create a query
        TypedQuery<Employee> theQuery = entityManager.createQuery("FROM Employee", Employee.class);

        // execute a query & get result list
        List<Employee> employees = theQuery.getResultList();

        // return the results
        return employees;
    }

    @Override
    public Employee findById(int id) {
        // get employee
        Employee employee = entityManager.find(Employee.class, id);

        // return employee
        return employee;
    }

    // save() will perform 2 operations depending upon the ID passed
    // id == 0 --> perform CREATE/INSERT
    // id != 0 --> perform UPDATE
    // merge() = saveOrUpdate() method of hibernate which is deprecated now.
    @Override
    public Employee save(Employee employee) {
        // save/update employee
        Employee dbEmployee = entityManager.merge(employee);

        // return newly created/updated employee
        return dbEmployee;
    }

    @Override
    public void deleteById(int id) {

        // find the employee by ID
        Employee employee = entityManager.find(Employee.class, id);

        // delete employee
        entityManager.remove(employee);
    }
}
