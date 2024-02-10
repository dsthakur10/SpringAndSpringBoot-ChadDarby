package com.devendra.aopdemo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class MembershipDAOImpl implements MembershipDAO {
    @Override
    public void addAccount() {
        System.out.println(getClass() + ": Doing my DB WORK: Adding a Membership Account");
    }

    @Override
    public String addSillyMember() {
        System.out.println(getClass() + ": Doing my DB WORK: Silly Class Member");
        return "Good bye";
    }

    @Override
    public void goToSleep() {
        System.out.println("GOOD NIGHT");
    }
}
