package com.bankapp.services;

import com.bankapp.dtos.Transaction;

public interface TransactionService {
    Transaction createTransaction (Transaction transaction);
    Transaction[] getTransactions (int accountNo);

}
