package com.bankapp.services;

import com.bankapp.exceptions.AccountAlreadyRegisteredException;
import com.bankapp.exceptions.AccountNotFoundException;
import com.bankapp.exceptions.IncorrectPasswordException;
import com.bankapp.dtos.Account;
import com.bankapp.dtos.Transaction;
import com.bankapp.exceptions.InsufficientBalanceException;

public class AccountServiceImpl implements AccountService, Subject {
    private static AccountServiceImpl instance;
    //Account array to store account objects for the application, later in the course
    //this array will be replaced with database
    private Account[] accounts;

    //counter is used to track how many accounts are present in the account array
    private int counter;


    private Observer[] observers;
    private int counterObservers;

    private AccountServiceImpl() {
        accounts = new Account[100];
        counter = 0;
        observers=new Observer[100];
        counterObservers=0;

    }

    public static AccountServiceImpl getInstance() {
        if (instance == null) {
            ServiceFactory serviceFactory=new ServiceFactory();
            instance=new AccountServiceImpl();
        }
        return instance;
    }





    public boolean login(Account account) throws AccountNotFoundException, IncorrectPasswordException {
        if (account == null) {
            throw new NullPointerException("Account Object was null");
        }
        for (int i = 0; i < counter; i++) {
            if (account.getAccountNo() == accounts[i].getAccountNo() && account.getPassword().equals(accounts[i].getPassword())) {
                return true;
            } else if (account.getAccountNo() == accounts[i].getAccountNo() && !account.getPassword().equals(accounts[i].getPassword())) {
                throw new IncorrectPasswordException("Password is not correct");
            }
        }
        throw new AccountNotFoundException("Account No does not exist");
    }

    public boolean register(Account account) throws AccountAlreadyRegisteredException {
        if (account == null) {
            throw new NullPointerException("Account object was null");
        }
        for (int i = 0; i < counter; i++) {
            if (account.getAccountNo() == accounts[i].getAccountNo()) {
                throw new AccountAlreadyRegisteredException("Account No ALready Registered");
            }
        }

        account.setBalance(0);
        accounts[counter++] = account;
        return true;
    }

    @Override
    public Account getAccount(int accountNo) throws AccountNotFoundException {
        for (int i = 0; i < counter; i++) {
            if (accounts[i].getAccountNo() == accountNo) {
                return accounts[i];
            }
        }
        throw new AccountNotFoundException("Account " + accountNo + "does not exist");
    }

    @Override
    public Account deposit(int accountNo, int amount) throws AccountNotFoundException {
        Account account = getAccount(accountNo);
        account.setBalance(account.getBalance() + amount);

        Transaction transaction = new Transaction();
        transaction.setAccountNo(accountNo);
        transaction.setDate("DD/MM/YYYY");
        transaction.setAction("Deposit ");
        transaction.setAmount(amount);
        notifyObserver(transaction);

        return account;
    }

    /*
     * A account holder can overdraw up to 1000 rs. This decision was made on
     * 13 July 2020. Please refer the business documents for more information.
     */
    @Override
    public Account withdraw(int accountNo, int amount) throws AccountNotFoundException, InsufficientBalanceException {
        Account account = getAccount(accountNo);
        if (account == null) {
            return null;
        }
        if ((account.getBalance() + 1000) < amount) {
            throw new InsufficientBalanceException("Can't withdraw " + amount + " when balance is " + account.getBalance());
        }
        account.setBalance(account.getBalance() - amount);

        Transaction transaction = new Transaction();
        transaction.setAccountNo(accountNo);
        transaction.setDate("DD/MM/YYYY");
        transaction.setAction("Withdraw");
        transaction.setAmount(amount);
        notifyObserver(transaction);

        return account;
    }


    //Here AccountServiceImpl class acts as the subject that is the observable
    //And the TransactionServiceImpl class observes the AccountServiceImpl class
    //Thus we use an observer pattern here
    @Override
    public void registerObserver(Observer observer) {
        observers[counterObservers++]=observer;

    }

    @Override
    public void removeObserver(Observer observer) {
        int i=0;
        for (;i<counterObservers;i++){
            if (observers[i].equals(observer)){
                break;
            }
        }

        if (i<counterObservers){
            for (;i<counterObservers;i++){
                observers[i]=observers[i+1];
            }
            counterObservers--;
        }
    }

    @Override
    public void notifyObserver(Object data) {
        for (Observer observer:observers){
            if (observer==null){
                break;
            }
            observer.update(data);
        }
    }


}
