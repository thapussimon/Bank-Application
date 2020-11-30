package com.bankapp.services;

import com.bankapp.exceptions.AccountAlreadyRegisteredException;
import com.bankapp.exceptions.AccountNotFoundException;
import com.bankapp.exceptions.IncorrectPasswordException;
import com.bankapp.dtos.Account;
import com.bankapp.exceptions.InsufficientBalanceException;

public interface AccountService {
    boolean login (Account account) throws AccountNotFoundException,IncorrectPasswordException;
    boolean register (Account account) throws AccountAlreadyRegisteredException;
    Account getAccount (int accountNo) throws AccountNotFoundException;
    Account deposit (int accountNo, int amount) throws AccountNotFoundException;
    Account withdraw (int accountNo, int amount) throws AccountNotFoundException, InsufficientBalanceException;




}
