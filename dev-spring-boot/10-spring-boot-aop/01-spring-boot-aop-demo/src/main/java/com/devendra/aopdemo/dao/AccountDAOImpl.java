package com.devendra.aopdemo.dao;

import com.devendra.aopdemo.Account;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAOImpl implements AccountDAO {
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
}
