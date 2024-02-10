package com.devendra.springboot.thymeleafdemo.controller;

import com.devendra.springboot.thymeleafdemo.entity.Employee;
import com.devendra.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		// create Model attribute to bind the form data
		Employee theEmployee = new Employee();
		theModel.addAttribute("employee", theEmployee);

		return "employees/employee-form";
	}

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId, Model theModel) {

		// get the employee from the service
		Employee theEmployee = employeeService.findById(theId);

		// set the employee in the model to prepopulate the form
		theModel.addAttribute("employee", theEmployee);

		// send over to form
		return "employees/employee-form";
	}

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// Get the employees from the Database
		List<Employee> theEmployees = employeeService.findAll();
		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		// save the employee
		employeeService.save(theEmployee);

		// use a redirect to avoid duplicate entries
		return "redirect:/employees/list";
	}

	// Why GetMapping instead of DeleteMapping for delete functionality ??
	// It's because we want to return the updated list of employees after deletion is completed
	// Fetching of results can only be done by GetMapping
	@GetMapping("/delete")
	public String delete(@RequestParam("employeeId") int theId) {
		// delete the employee
		employeeService.deleteById(theId);

		// redirect
		return "redirect:/employees/list";
	}

}


//
//	@PostConstruct
//	private void loadData() {
//
//		// create employees
//		Employee emp1 = new Employee("Leslie", "Andrews", "leslie@luv2code.com");
//		Employee emp2 = new Employee("Emma", "Baumgarten", "emma@luv2code.com");
//		Employee emp3 = new Employee("Avani", "Gupta", "avani@luv2code.com");
//
//		// create the list
//		theEmployees = new ArrayList<>();
//
//		// add to the list
//		theEmployees.add(emp1);
//		theEmployees.add(emp2);
//		theEmployees.add(emp3);
//	}