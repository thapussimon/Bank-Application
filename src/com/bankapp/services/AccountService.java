package com.bankapp.services;

import com.bankapp.Account;
import com.bankapp.Transaction;

public interface AccountService {
    boolean login (int accountNo, String password);
    boolean register (int accountNo, String password);
    Account getAccount(int accountNo);
    Account deposit(int accountNo,int amount);
    Account withdraw(int accountNo,int amount);
    Transaction createTransaction(Transaction transaction);
    Transaction[] getTransactions(int accountNo);



}
