package com.devendra.aopdemo;

import com.devendra.aopdemo.dao.AccountDAO;
import com.devendra.aopdemo.dao.MembershipDAO;
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
	public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
		return runner -> {

			demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
			demoTheAfterReturningAdvice(theAccountDAO);
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

		System.out.println("\n------ Main Program -----");
		System.out.println(theAccounts);
	}

}
