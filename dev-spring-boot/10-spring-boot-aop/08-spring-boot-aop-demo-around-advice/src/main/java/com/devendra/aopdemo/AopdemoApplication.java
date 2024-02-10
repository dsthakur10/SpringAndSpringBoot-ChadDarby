package com.devendra.aopdemo;

import com.devendra.aopdemo.dao.AccountDAO;
import com.devendra.aopdemo.dao.MembershipDAO;
import com.devendra.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(AopdemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
											   MembershipDAO theMembershipDAO,
											   TrafficFortuneService theTrafficFortuneService) {
		return runner -> {

			// demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
			// demoTheAfterReturningAdvice(theAccountDAO);
			// demoTheAfterThrowingAdvice(theAccountDAO);
			// demoTheAfterAdvice(theAccountDAO);
			//demoTheAroundAdvice(theTrafficFortuneService);
			demoTheAroundAdviceHandleException(theTrafficFortuneService);
		};
	}

	private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {

		// call the business method
		theAccountDAO.addAccount();

		//theMembershipDAO.addAccount();
		//theMembershipDAO.addSillyMember();

		Account account1 = new Account("Devendra", "ADMIN");
		Account account2 = new Account("Thakur", "MANAGER");

		theAccountDAO.addAccount(account1);
		theAccountDAO.addAccount(account2, true);

		theAccountDAO.doWork();
		//theMembershipDAO.goToSleep();

		theAccountDAO.setName("Messi");
		theAccountDAO.setServiceCode("LM10");

		theAccountDAO.getName();
		theAccountDAO.getServiceCode();
	}

	private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {

		List<Account> theAccounts = theAccountDAO.findAccounts();

		System.out.println("\n------ Main Program : demoTheAfterReturningAdvice -----");
		System.out.println(theAccounts);
	}

	private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {

		List<Account> theAccounts = null;

		try {
			// add boolean flag to simulate exception
			boolean flag = true;
			theAccounts = theAccountDAO.findAccounts(flag);
		} catch (Exception exc) {
			System.out.println("\nMain Program --> Caught exception: " + exc);
		}

		System.out.println("\n------ Main Program : demoTheAfterThrowingAdvice -----");
		System.out.println(theAccounts);
	}

	private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
		List<Account> theAccounts = null;

//		try {
//			// add boolean flag to simulate exception
//			boolean flag = true;
//			theAccounts = theAccountDAO.findAccounts(flag);
//		} catch (Exception exc) {
//			System.out.println("\nMain Program --> Caught exception: " + exc);
//		}

		theAccounts = theAccountDAO.findAccounts();
		System.out.println("\n------ Main Program : demoTheAfterThrowingAdvice -----");
		System.out.println(theAccounts);
	}

	private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {

		System.out.println("\n------ Main Program : demoTheAroundAdvice -----");

		String data = theTrafficFortuneService.getFortune();

		System.out.println("My fortune is: " + data);
	}

	private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
		System.out.println("\n------ Main Program : demoTheAroundAdviceHandleException -----");

		boolean flag = true;
		String data = theTrafficFortuneService.getFortune(flag);

		System.out.println("My fortune is: " + data);
	}

}
