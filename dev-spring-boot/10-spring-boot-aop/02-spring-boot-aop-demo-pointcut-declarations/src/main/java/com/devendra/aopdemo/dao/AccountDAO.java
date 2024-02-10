package com.devendra.aopdemo.dao;

import com.devendra.aopdemo.Account;

public interface AccountDAO {
    void addAccount();
    void addAccount(Account theAccount);
    void addAccount(Account theAccount, boolean isVIP);
    void doWork();

    String getName();
    void setName(String name) ;
    String getServiceCode();
    void setServiceCode(String serviceCode);
}
