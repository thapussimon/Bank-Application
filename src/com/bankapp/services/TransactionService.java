package com.bankapp.services;

import com.bankapp.Transaction;

public interface TransactionService {
    Transaction createTransaction (Transaction transaction);
    Transaction[] getTransactions (int accountNo);

}
