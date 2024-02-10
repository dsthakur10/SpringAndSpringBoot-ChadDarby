package com.example.devendra.cruddemo.rest;

import com.example.devendra.cruddemo.dao.EmployeeDAO;
import com.example.devendra.cruddemo.dao.EmployeeDAOImpl;
import com.example.devendra.cruddemo.entity.Employee;
import com.example.devendra.cruddemo.service.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

    // Without using Service implementation
    /*
    private final EmployeeDAO employeeDAO;

    // quick & dirty: Inject Employee DAO

    public EmployeeRestController(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }

    // expose "/employees" & return list of all employees
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
    */

    private final EmployeeService employeeService;

    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    // expose "/employees" & return list of all employees (READ)
    @GetMapping("/employees")
    public List<Employee> findAll() {
        return employeeService.findAll();
    }


    // Get the Employee with given ID (READ)
    @GetMapping("/employees/{employeeId}")
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findById(employeeId);

        if(employee == null) {
            throw new RuntimeException("Employee ID not found - " + employeeId);
        }

        return employee;
    }


    // Add new employee (CREATE)

    /* Body of Request message
    {
        "firstName" : "Devendra",
        "lastName" : "Thakur",
        "email" : "devthakur@gmail.com"
    }
    */
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee employee) {
        // Just in case user pass an ID in JSON in the body of POST --> set ID to 0
        // this is to force a save of new item, instead of Update
        //employee.setId(0);

        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // Update an existing employee (UPDATE)

    /*  Body of Request message
    {
        "id" : 2,
        "firstName" : "Hermione",
        "lastName" : "Granger",
        "email" : "hermionegranger@hogwarts.magic"
    }
    */
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee) {

        Employee dbEmployee = employeeService.save(employee);
        return dbEmployee;
    }

    // delete an employee
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId) {
        Employee tempEmployee = employeeService.findById(employeeId);

        // throw exception if employee NOT found
        if(tempEmployee == null) {
            throw new RuntimeException("Employee ID not found: " + employeeId);
        }

        employeeService.deleteById(employeeId);

        return "Deleted employee: " + employeeId;
    }
}


/*

We have not put a check on CREATE(POST) & UPDATE(PUT) requests.

    CREATE will by default create an object of Employee with the auto-incremented primary key
    Hence, there's no need to pass ID. Even if you do, there will be no effect of ID on newly created
    object because, JPA will anyway create this object with next auto-incremented ID

    UPDATE will take ID as input & will perform either of 2 operations:
    1. ID found --> UPDATE the details as given in request body
    2. ID NOT found --> CREATE new object with auto-incremented ID

 */