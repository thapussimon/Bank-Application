package com.bankapp;

import com.bankapp.services.AccountService;
import com.bankapp.services.AccountServiceImpl;

import java.util.Scanner;


public class Application {
    //This contains the UI logic

    private Scanner scanner;
    private AccountService accountService;
    //a flag used to check whether a user is logged in or not
    private boolean isLoggedIn;

    //an attribute to store account no of the logged in user
    private int loggedInAccountNo;

    public Application (AccountService accountService) {
        scanner=new Scanner(System.in);
        this.accountService=accountService;
        isLoggedIn=false;
        loggedInAccountNo=0;
    }

    private void start () {
        boolean flag = true;

        System.out.println("*********************");
        System.out.println("*******Bank App******");
        System.out.println("*********************");

        do {
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Account");
            System.out.println("4. Deposit");
            System.out.println("5. Withdraw");
            System.out.println("6. Account Statement");
            System.out.println("7. Logout");
            System.out.println("8. Exit");

            System.out.print("\nPlease select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1": login(); break;
                case "2": register(); break;
                case "3": getAccount(); break;
                case "4": deposit(); break;
                case "5": withdraw(); break;
                case "6": getAccountStatement(); break;
                case "7": logout(); break;
                case "8": flag=false; break;
                default:  System.out.println("Error"); break;
            }
        } while (flag);
    }

    //This method is used to perform login function for the user.
    //If the user is already logged in, then he won't be able to login again.
    //Also a user can only login, if the account no and password provided by
    //the user are present in the accounts array.
    private void login () {
        if (isLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("********Login********");
        System.out.println("*********************");

        System.out.print("Account No.:");
        int accountNo = Integer.parseInt(scanner.nextLine());

        System.out.print("Password:");
        String password = scanner.nextLine();

        if (accountService.login(accountNo, password)) {
            System.out.println("You are logged in.");
            isLoggedIn = true;
            loggedInAccountNo = accountNo;
        } else {
            System.out.println("Incorrect Username / Password");
        }
    }

    //This method is used to perform register function for the user.
    //If the user is already logged in, then he won't be able to register.
    //Also a user can only register, if the account no and password provided by
    //the user are not present in the accounts array.
    private void register () {
        if (isLoggedIn) {
            System.out.println("You are already logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("******Register*******");
        System.out.println("*********************");

        System.out.print("Account No.:");
        int accountNo = Integer.parseInt(scanner.nextLine());

        System.out.print("Password:");
        String password = scanner.nextLine();

        if (accountService.register(accountNo, password)) {
            System.out.println("You are logged in.");
            isLoggedIn = true;
            loggedInAccountNo = accountNo;
        } else {
            System.out.println("User already exists.");
        }
    }

    private void getAccount () {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*******Account*******");
        System.out.println("*********************");

        System.out.println("Get the account corresponding to Account No: " + loggedInAccountNo);
    }

    private void deposit () {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*******Deposit*******");
        System.out.println("*********************");

        System.out.print("Amount: ");
        int amount = Integer.parseInt(scanner.nextLine());

        System.out.println("Deposit " + amount + " rs to account " + loggedInAccountNo);
    }

    private void withdraw () {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("******Withdraw*******");
        System.out.println("*********************");

        System.out.print("Amount: ");
        int amount = Integer.parseInt(scanner.nextLine());

        System.out.println("Withdraw " + amount + " rs from account " + loggedInAccountNo);
    }

    private void getAccountStatement() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("**Account Statement**");
        System.out.println("*********************");

        System.out.println("Print account statement for account " + loggedInAccountNo);
    }

    private void logout () {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }
        System.out.println("Logged out successfully");
        isLoggedIn = false;
        loggedInAccountNo = 0;
    }

    public static void main(String[] args) {
        AccountService accountService = new AccountServiceImpl();
        Application application = new Application(accountService);
        application.start();

    }











}



