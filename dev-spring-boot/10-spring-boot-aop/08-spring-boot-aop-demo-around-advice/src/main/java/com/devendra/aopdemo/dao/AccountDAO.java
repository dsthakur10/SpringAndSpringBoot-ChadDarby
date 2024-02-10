package com.devendra.aopdemo.dao;

import com.devendra.aopdemo.Account;

import java.util.List;

public interface AccountDAO {
    void addAccount();
    void addAccount(Account theAccount);
    void addAccount(Account theAccount, boolean isVIP);
    void doWork();

    String getName();
    void setName(String name) ;
    String getServiceCode();
    void setServiceCode(String serviceCode);

    // add a new method --> findAccounts()
    List<Account> findAccounts();
    List<Account> findAccounts(boolean flag);
}
