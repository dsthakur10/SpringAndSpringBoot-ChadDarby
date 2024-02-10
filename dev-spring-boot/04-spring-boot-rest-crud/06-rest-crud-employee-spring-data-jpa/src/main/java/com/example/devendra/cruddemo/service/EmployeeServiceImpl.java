package com.example.devendra.cruddemo.service;

import com.example.devendra.cruddemo.dao.EmployeeRepository;
import com.example.devendra.cruddemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService{

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // delegate the calls to JPARepository
    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
        //return employeeRepository.findById(id); --> Produces error

        Optional<Employee> result = employeeRepository.findById(id);

        if(result.isPresent()) {
            return result.get();
        }

        throw new RuntimeException("Did not find Employee ID: " + id);
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}

// No need to use @Transactional. JPARepository does it for you on its own