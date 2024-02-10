package com.devendra.cruddemo;

import com.devendra.cruddemo.dao.StudentDAO;
import com.devendra.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO) {
		return runner -> {
			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
			//readStudent(studentDAO);
			//queryForStudents(studentDAO);
			//queryForStudentsByLastName(studentDAO);
			//updateStudent(studentDAO);
			//deleteStudent(studentDAO);
			//deleteAllStudents(studentDAO);
		};
	}

	private void createStudent(StudentDAO studentDAO) {

		// create the student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Leo", "Messi", "messi@football.com");

		// save the student object in DB
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		// Display the ID of the saved student
		System.out.println("Saved student | Generated id: " + tempStudent.getId());
	}


	private void createMultipleStudents(StudentDAO studentDAO) {

		// create multiple students
		System.out.println("Creating 3 new student object...");
		Student tempStudent1 = new Student("Cristiano", "Ronaldo", "cr7@football.com");
		Student tempStudent2 = new Student("Neymar", "Junior", "neymarjr@football.com");
		Student tempStudent3 = new Student("Luis", "Saurez", "saurez@football.com");

		// save the student objects
		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);

	}


	private void readStudent(StudentDAO studentDAO) {

		Student myStudent = studentDAO.findById(4);

		System.out.println("Retrieving student with ID: 4");
		System.out.println("Found the student: " + myStudent);
	}


	private void queryForStudents(StudentDAO studentDAO) {
		// get a list of students
		List<Student> theStudents = studentDAO.findAll();

		// display all students
		for(Student tempStudent : theStudents) {
			System.out.println(tempStudent);
		}
	}


	// JPQL - Named Parameters
	private void queryForStudentsByLastName(StudentDAO studentDAO) {

		// get a list of students
		List<Student> theStudents = studentDAO.findByLastName("messi");

		// display all the students with given last Name
		for(Student student: theStudents) {
			System.out.println(student);
		}
	}


	private void updateStudent(StudentDAO studentDAO) {

		// retrieve student based on ID
		int studentId = 4;
		System.out.println("Getting student with ID: " + studentId);
		Student myStudent = studentDAO.findById(studentId);

		// change the first Name of student
		System.out.println("Updating the student...");
		myStudent.setFirstName("Vinicious");

		// update the student
		studentDAO.update(myStudent);

		// display the updated student
		System.out.println("Updated student: " + myStudent);
	}

	private void deleteStudent(StudentDAO studentDAO) {
		int studentId = 5;
		System.out.println("Deleting student with Id: " + studentId);

		studentDAO.delete(studentId);
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		System.out.println("Deleting all Students");
		int numDeletedRows = studentDAO.deleteAll();

		System.out.println("Number of Deleted rows = " + numDeletedRows);
	}

}

