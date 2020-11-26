import java.util.Scanner;

public class Application {
    private Scanner scanner;

    //To store Account objects in the application
    private Account[] accounts;

    //Used to track how many accounts are there in the Account array
    private int counter;

    //A flag used to check whether the user is logged in or not
    private boolean isLoggedIn;

    //To store the Account Number of the logged in user
    private int loggedInAccountNo;

    public Application() {
        scanner = new Scanner(System.in);
        accounts = new Account[100];
        counter = 0;
        isLoggedIn = false; //by default no body is logged in
        loggedInAccountNo = 0;
    }

    public void start() {
        boolean flag = true;
        System.out.println("********************");
        System.out.println("*******BANK APP*****");
        System.out.println("********************");

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
                case "1":
                    login();
                    break;
                case "2":
                    register();
                    break;
                case "3":
                    getAccount();
                    break;
                case "4":
                    deposit();
                    break;
                case "5":
                    withdraw();
                    break;
                case "6":
                    getAccountStatement();
                    break;
                case "7":
                    logout();
                    break;
                case "8":
                    flag = false;
                    break;
                default:
                    System.out.println("Error");
                    break;
            }


        } while (flag);

    }


    //This method is used to perform login function for the user.
    //If the user is already logged in, then he won't be able to login again.
    //Also a user can only login, if the account no and password provided by
    //the user are present in the accounts array.

    public void login() {
        if (isLoggedIn) {
            System.out.println("You are already logged in");
            return;
        }
        System.out.println("*********************");
        System.out.println("********Login********");
        System.out.println("*********************");

        System.out.print("Account No.:");
        int accountNo = Integer.parseInt(scanner.nextLine());

        System.out.print("Password:");
        String password = scanner.nextLine();

        //We are searching in the account array if the given account number & password matches
        //That is why we are initializing i=0 & searching till counter
        //The counter would give the current number of account holders
        for (int i = 0; i < counter; i++) {
            if (accountNo == accounts[i].getAccountNo() && password.equals(accounts[i].getPassword())) {
                System.out.println("You are logged in");
                isLoggedIn = true; //Right now the user is loggedin
                loggedInAccountNo = accountNo; //Those who are logged in , their accountNo is stored in a duplicate variable
                return;
            }
        }
        //If the account Number & password does not match with the Account array
        System.out.println("Incorrect Username/Password");
    }

    //This method is used to perform register function for the user.
    //If the user is already logged in, then he won't be able to register.
    //Also a user can only register, if the account no and password provided by
    //the user are not present in the accounts array.

    private void register() {
        if (isLoggedIn) {
            System.out.println("You are already logged in");
            return;
        }
        System.out.println("*********************");
        System.out.println("******Register*******");
        System.out.println("*********************");
        System.out.print("Account No.:");
        int accountNo = Integer.parseInt(scanner.nextLine());

        System.out.print("Password:");
        String password = scanner.nextLine();

        //If The account number matches with the accounts array object ->User already exists
        for (int i = 0; i < counter; i++) {
            if (accountNo == accounts[i].getAccountNo()) {
                System.out.println("User already registered");
                return;
            }
        }

        //If the account number does not matches, we create a new object and initialize with the values
        Account tempAccount = new Account();
        tempAccount.setAccountNo(accountNo);
        tempAccount.setPassword(password);
        tempAccount.setBalance(0);
        accounts[counter++] = tempAccount;
        System.out.println("You are already logged in");
        isLoggedIn = true;
        loggedInAccountNo = accountNo;
    }


    private void getAccount() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }

        System.out.println("*********************");
        System.out.println("*******Account*******");
        System.out.println("*********************");

        System.out.println("Get the account corresponding to Account No: " + loggedInAccountNo);
    }

    private void deposit() {
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

    private void withdraw() {
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

    private void logout() {
        if (!isLoggedIn) {
            System.out.println("You are not logged in.");
            return;
        }
        System.out.println("Logged out successfully");
        isLoggedIn = false;
        loggedInAccountNo = 0;
    }

    public static void main(String[] args) {
        Application application = new Application();
        application.start();
    }
}



