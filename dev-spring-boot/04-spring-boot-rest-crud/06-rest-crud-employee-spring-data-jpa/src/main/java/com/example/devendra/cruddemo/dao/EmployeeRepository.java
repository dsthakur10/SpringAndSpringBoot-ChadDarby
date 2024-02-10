package com.example.devendra.cruddemo.dao;

import com.example.devendra.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

// <Entity type, primary key>
public interface EmployeeRepository extends JpaRepository<Employee, Integer>{
    // no need to write any code
    // We will get every CRUD operations for free from JPARepository
}
