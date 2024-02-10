package com.devendra.advancedmappingdemo;

import com.devendra.advancedmappingdemo.dao.AppDAO;
import com.devendra.advancedmappingdemo.entity.Instructor;
import com.devendra.advancedmappingdemo.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AdvancedmappingdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedmappingdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AppDAO appDAO) {

		return runner -> {
			createInstructor(appDAO);
			//findInstructor(appDAO);
			//deleteInstructor(appDAO);
			//findInstructorDetails(appDAO);
			//deleteInstructorDetail(appDAO);
			//deleteInstructorDetailOneDay(appDAO);
		};
	}

	private void createInstructor(AppDAO appDAO) {

		// create the instructor
		Instructor theInstructor =
				new Instructor("Devendra", "Thakur", "devthakur@gmail.com");

		// create the instructor Details
		InstructorDetail theInstructorDetail =
				new InstructorDetail("http://www.memersadda.com/youtube", "Cricket");

		// associate the objects
		theInstructor.setInstructorDetail(theInstructorDetail);

		// save the instructor
		// This will also save instructor_detail object because of CascadeType.ALL
		appDAO.save(theInstructor);


//		// create the instructor
//		Instructor theInstructor =
//				new Instructor("Leo", "Messi", "leomessi@gmail.com");
//
//		// create the instructor Details
//		InstructorDetail theInstructorDetail =
//				new InstructorDetail("http://www.messileo.com/youtube", "Boga Jonito");
//
//		// associate the objects
//		theInstructor.setInstructorDetail(theInstructorDetail);
//
//		// save the instructor
//		// This will also save instructor_detail object because of CascadeType.ALL
//		appDAO.save(theInstructor);

		System.out.println("Saving Instructor: " + theInstructor);
	}

	private void findInstructor(AppDAO appDAO) {

		int theId = 1;
		Instructor instructor = appDAO.findInstructorById(theId);

		System.out.println("Instructor: " + instructor);
	}

	private void deleteInstructor(AppDAO appDAO) {

		int theId = 1;
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
	private void deleteInstructorDetailOneDay(AppDAO appDAO) {
		int theId = 5;
		appDAO.deleteInstructorDetailByIdOneWay(theId);

		System.out.println("Deleted Instructor Detail ONLY: " + theId);
	}
}
