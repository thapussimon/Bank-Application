package com.bankapp.services;

import com.bankapp.Account;
import com.bankapp.Transaction;

public interface AccountService {
    boolean login (Account account);
    boolean register (Account account);
    Account getAccount (int accountNo);
    Account deposit (int accountNo, int amount);
    Account withdraw (int accountNo, int amount);
    Transaction createTransaction (Transaction transaction);
    Transaction[] getTransactions (int accountNo);



}
