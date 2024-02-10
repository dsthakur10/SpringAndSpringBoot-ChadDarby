package com.devendra.advancedmappingdemo;

import com.devendra.advancedmappingdemo.dao.AppDAO;
import com.devendra.advancedmappingdemo.entity.Course;
import com.devendra.advancedmappingdemo.entity.Instructor;
import com.devendra.advancedmappingdemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AdvancedmappingdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedmappingdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {

			// One-to-One Unidirectional

			//createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);

			// One-to-One Bidirectional

			//findInstructorDetails(appDAO);
			//deleteInstructorDetail(appDAO);
			//deleteInstructorDetailOneWay(appDAO);

			// One-to-Many Bidirectional

			//createInstructorWithCourses(appDAO);
			//findInstructorWithCourses(appDAO);
			//findCoursesForInstructor(appDAO);
			//findCoursesForInstructorJoinFetch(appDAO);
			//updateInstructor(appDAO);
			//updateCourse(appDAO);
			//deleteInstructor(appDAO);
			deleteCourse(appDAO);

		};
	}


	private void createInstructor(AppDAO appDAO) {

//		// create the instructor
//		Instructor theInstructor =
//				new Instructor("Devendra", "Thakur", "devthakur@gmail.com");
//
//		// create the instructor Details
//		InstructorDetail theInstructorDetail =
//				new InstructorDetail("http://www.memersadda.com/youtube", "Cricket");
//
//		// associate the objects
//		theInstructor.setInstructorDetail(theInstructorDetail);
//
//		// save the instructor
//		// This will also save instructor_detail object because of CascadeType.ALL
//		appDAO.save(theInstructor);


		// create the instructor
		Instructor theInstructor =
				new Instructor("Leo", "Messi", "leomessi@gmail.com");

		// create the instructor Details
		InstructorDetail theInstructorDetail =
				new InstructorDetail("http://www.messileo.com/youtube", "Boga Jonito");

		// associate the objects
		theInstructor.setInstructorDetail(theInstructorDetail);

		// save the instructor
		// This will also save instructor_detail object because of CascadeType.ALL
		appDAO.save(theInstructor);

		System.out.println("Saving Instructor: " + theInstructor);
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + instructor);
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 2;
		appDAO.deleteInstructorById(theId);

		System.out.println("Deleted Instructor ID: " + theId);
	}


	// Bi-Directional setup
	private void findInstructorDetails(AppDAO appDAO) {
		int theId = 2;
		InstructorDetail instructorDetail = appDAO.findInstructorDetailsById(theId);

		System.out.println("Instructor_details: " + instructorDetail);

		// Get the associated instructor
		System.out.println("Instructor: " + instructorDetail.getInstructor());
	}

	private void deleteInstructorDetail(AppDAO appDAO) {

		int theId = 3;
		appDAO.deleteInstructorDetailById(theId);

		System.out.println("Deleted Instructor Detail: " + theId);
	}


	// Deleting only one-side entity without affecting the other side
	private void deleteInstructorDetailOneWay(AppDAO appDAO) {
		int theId = 5;
		appDAO.deleteInstructorDetailByIdOneWay(theId);

		System.out.println("Deleted Instructor Detail ONLY: " + theId);
	}


	private void createInstructorWithCourses(AppDAO appDAO) {
//		// create the instructor
//		Instructor theInstructor =
//				new Instructor("Devendra", "Thakur", "devthakur@gmail.com");
//
//		// create the instructor Details
//		InstructorDetail theInstructorDetail =
//				new InstructorDetail("http://www.memersadda.com/youtube", "Cricket");
//
//		// create the Course objects
//		Course theCourse1 = new Course("Discrete Mathematics");
//		Course theCourse2 = new Course("Engineering Mathematics");
//		Course theCourse3 = new Course("Geometry");
//
//		// associate the objects
//		theInstructor.setInstructorDetail(theInstructorDetail);
//
//		theInstructor.add(theCourse1);
//		theInstructor.add(theCourse2);
//		theInstructor.add(theCourse3);

		// create the instructor
		Instructor theInstructor =
				new Instructor("Leo", "Messi", "leomessi@gmail.com");

		// create the instructor Details
		InstructorDetail theInstructorDetail =
				new InstructorDetail("http://www.messileo.com/youtube", "Boga Jonito");

		// create the Course objects
		Course theCourse1 = new Course("How to score Freekick");
		Course theCourse2 = new Course("Playmaker course");

		// associate the objects
		theInstructor.setInstructorDetail(theInstructorDetail);

		theInstructor.add(theCourse1);
		theInstructor.add(theCourse2);


		// save the instructor
		// This will also save InstructorDetail object & Course objects because of CascadeType.ALL
		appDAO.save(theInstructor);

		System.out.println("Saving the Instructor: " + theInstructor);
		System.out.println("The courses offered: " + theInstructor.getCourses());

	}


	// requires One-To-Many mapping set to Eager
	private void findInstructorWithCourses(AppDAO appDAO) {

		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("The Instructor: " + instructor);
		System.out.println("Associated courses: " + instructor.getCourses());
	}


	// Option-1 --> Create separate query to fetch courses
	private void findCoursesForInstructor(AppDAO appDAO) {
		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);
		System.out.println("The Instructor: " + instructor);

		// find courses associated with Instructor
		List<Course> courses = appDAO.findCoursesByInstructorId(theId);

		// Associate these courses with Instructor
		instructor.setCourses(courses);

		System.out.println("Associated courses: " + instructor.getCourses());
	}

	private void findCoursesForInstructorJoinFetch(AppDAO appDAO) {
		int theId = 1;

		Instructor instructor = appDAO.findInstructorByIdJoinFetch(theId);

		System.out.println("The Instructor: " + instructor);
		System.out.println("Associated courses: " + instructor.getCourses());
	}

	private void updateInstructor(AppDAO appDAO) {

		int theId = 1;

		// find the instructor
		Instructor tempInstructor = appDAO.findInstructorById(theId);

		// Update instructor
		tempInstructor.setLastName("Tendulkar");
		appDAO.update(tempInstructor);

		System.out.println("Updated Instructor: " + theId);
	}

	private void updateCourse(AppDAO appDAO) {
		int theId = 12;

		// find the course
		Course tempCourse = appDAO.findCourseById(theId);

		// Update course
		tempCourse.setTitle("Algebra");
		appDAO.update(tempCourse);

		System.out.println("Updated course: " + theId);
	}

	private void deleteCourse(AppDAO appDAO) {

		int theId = 12;
		appDAO.deleteCourseById(theId);

		System.out.println("Deleted the course: " + theId);
	}


}
