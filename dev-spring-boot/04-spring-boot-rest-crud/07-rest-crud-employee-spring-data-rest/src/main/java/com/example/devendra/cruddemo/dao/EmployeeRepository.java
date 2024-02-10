package com.example.devendra.cruddemo.dao;

import com.example.devendra.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

// <Entity type, primary key>
//public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
//    // no need to write any code
//    // We will get every CRUD operations for free from JPARepository
//}

@RepositoryRestResource(path="members")
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    // no need to write any code
    // We will get every CRUD operations for free from JPARepository
}