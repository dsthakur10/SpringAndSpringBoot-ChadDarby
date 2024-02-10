package com.devendra.aopdemo.dao;

import com.devendra.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public void addAccount() {
        System.out.println(getClass() + ": Doing my DB WORK: Adding an Account");

        // We can use JPA/Hibernate here for DB
    }

    @Override
    public void addAccount(Account theAccount) {

        System.out.println(getClass() + ": Doing my DB WORK: Adding an Account with given Account");
        System.out.println("\nName = " + theAccount.getName() + " Level = " + theAccount.getLevel());
    }

    @Override
    public void addAccount(Account theAccount, boolean isVIP) {
        System.out.println(getClass() + ": Doing my DB WORK: Adding an Account with given Account & VIP flag");
        System.out.println("\nName = " + theAccount.getName() + " Level = " + theAccount.getLevel() + " VIP person = " + isVIP);
    }

    @Override
    public void doWork() {
        System.out.println("GYM for 1 hour");
    }

    public String getName() {
        System.out.println("Getter for Name");
        return name;
    }

    public void setName(String name) {
        System.out.println("Setter for Name");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println("Getter for ServiceCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println("Setter for ServiceCode");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean flag) {

        if(flag) {
            throw new RuntimeException("No Soup for you !!!");
        }

        List<Account> myAccounts = new ArrayList<>();

        // Create sample accounts
        Account acc1 = new Account("Ronaldo", "ADMIN");
        Account acc2 = new Account("Messi", "ADMIN");
        Account acc3 = new Account("Neymar", "MANAGER");

        // add them to list
        myAccounts.add(acc1);
        myAccounts.add(acc2);
        myAccounts.add(acc3);

        return myAccounts;
    }

}
