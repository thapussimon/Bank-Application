package com.bankapp.services;

import com.bankapp.Account;


public class AccountService {
    //This contains the business logic
    //To store bankapp.Account objects in the application
    private Account[] accounts;

    //Used to track how many accounts are there in the bankapp.Account array
    private int counter;

    public AccountService() {
        accounts = new Account[100];
        counter = 0;
    }

    public boolean login(int accountNo, String password) {
        for (int i = 0; i < counter; i++) {
            if (accountNo == accounts[i].getAccountNo() && password.equals(accounts[i].getPassword())) {
                return true;

            }

        }
        return false;
    }

    public boolean register(int accountNo, String password) {
        for (int i = 0; i < counter; i++) {
            if (accountNo == accounts[i].getAccountNo()) {
                return false;
            }
        }

        Account temp = new Account();
        temp.setAccountNo(accountNo);
        temp.setPassword(password);
        temp.setBalance(0);
        accounts[counter++] = temp;
        return true;

    }
}
