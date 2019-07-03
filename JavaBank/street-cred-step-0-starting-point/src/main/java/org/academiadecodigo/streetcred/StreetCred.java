package org.academiadecodigo.streetcred;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerSetInputScanner;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringSetInputScanner;
import org.academiadecodigo.streetcred.domain.Bank;
import org.academiadecodigo.streetcred.domain.Customer;
import org.academiadecodigo.streetcred.domain.account.Account;
import org.academiadecodigo.streetcred.domain.account.AccountType;
import org.academiadecodigo.streetcred.domain.account.CheckingAccount;
import org.academiadecodigo.streetcred.domain.account.SavingsAccount;
import org.academiadecodigo.streetcred.managers.AccountManager;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class StreetCred {

    private Prompt prompt = new Prompt(System.in, System.out);
    private Bank bank;
    private AccountManager manager;
    private Customer currentCustomer;
    private Account currentAccount;
    private Customer destinationAccHolder;
    private Map<Integer, Operation> operationMap;


    public StreetCred(Bank bank) {
        this.bank = bank;
        this.manager = bank.getAccountManager();
        buildMap();
    }

    private void buildMap() {
        operationMap = new HashMap<>();
        // populate map
    }

    public void open() {

        System.out.println("\n\"Welcome to bank, the number one code-bank in the world.\"");
        getCustomerNumber();
        serve();
    }

    private void getCustomerNumber() {

        IntegerSetInputScanner getCustNum = new IntegerSetInputScanner(bank.getCustomerIds());
        getCustNum.setMessage("\nPlease provide your customer number : ");
        getCustNum.setError("\nPlease provide a valid customer number!");

        int customerIDInput = prompt.getUserInput(getCustNum);

        currentCustomer = bank.getCustomer(customerIDInput);
        System.out.println("\nAh, good morning, Mr. " + currentCustomer.getName() +
                " = = = = = = = = = = = = = = = = = = = = = = = = = =");

    }

    private void serve() {

        String[] operations = {"Check Balance", "Deposit", "Withdraw", "Open Account", "Transfer", "Leave"};

        while (true) {

            MenuInputScanner menu = new MenuInputScanner(operations);
            menu.setMessage("What would you like to do?");
            menu.setError("\"I'm afraid we do not provide such services at this desk,\" the clerk says, visibly startled " +
                    "at your request.");

            processRequest(prompt.getUserInput(menu));
        }
    }

    private void processRequest(int requestNum) {

        // strategy dp
        operationMap.get(requestNum).execute();

        switch (requestNum) {

            case 1:
                checkBalance();
                break;
            case 2:
                deposit();
                break;
            case 3:
                withdraw();
                break;
            case 4:
                openAccount();
                break;
            case 5:
                transfer();
                break;
            case 6:
                quit();
                break;
            default:
                break;
        }
    }

    private void checkBalance() {

        // iterate through all accounts and print balance
        // show total
        if (currentAccount == null) {

            chooseAccount(currentCustomer);
        }

        double balance = currentAccount.getBalance();

        System.out.println("\nYour account with account number " + currentAccount.getId() +
                " currently has a balance of : " + balance);

        currentAccount = null;
    }

    private void deposit() {

        if (currentAccount == null) {

            chooseAccount(currentCustomer);
        }

        double amount = chooseDepositAmount();

        manager.deposit(currentAccount.getId(), amount);
        System.out.println("\nSuccessful deposit of: " + amount);
        currentAccount = null;
    }

    private void withdraw() {

        if (currentAccount == null) {

            chooseAccount(currentCustomer);
        }

        if (currentAccount instanceof SavingsAccount && currentCustomer.getAccountMap().size() < 2) {

            System.out.println("\nYou may not withdraw from a savings account!");
            serve();
        }

        if (currentAccount instanceof SavingsAccount) {

            System.out.println("\nYou may not withdraw from a savings account! Please choose another account.");
            chooseAccount(currentCustomer);
        }

        double amount = chooseWithdrawalAmount();

        double balance = currentAccount.getBalance();

        System.out.println(amount > balance ? "\nYou don't have enough funds for this operation." :
                currentAccount instanceof SavingsAccount && balance - amount < 100 ?
                        "\nThe balance of your savings account must never dip below 100@" : "\nSuccessful withdrawal of: " + amount);


        manager.withdraw(currentAccount.getId(), amount);
        currentAccount = null;
    }

    private void openAccount() {

        int accountNum = currentCustomer.openAccount(chooseAccType());
        currentAccount = manager.getAccount(accountNum);

        System.out.println(currentAccount instanceof CheckingAccount ?
                "\nSuccess, you have just opened a checking account. Your account number is : " + accountNum :
                "\nSuccess, you have just opened a savings account. Your account number is : " + accountNum);


        stateNumOfAccounts();
        offerToContinue();
    }

    private AccountType chooseAccType() {

        String[] accType = {"Checkings", "Savings"};

        MenuInputScanner chooseAccType = new MenuInputScanner(accType);
        chooseAccType.setMessage("What type of account do you want to open?");
        chooseAccType.setError("You must choose one of the account types.");

        if (prompt.getUserInput(chooseAccType) == 2) {
            return AccountType.SAVINGS;
        }
        return AccountType.CHECKING;
    }


    private void offerToContinue() {

        Set<java.lang.String> options = new HashSet<>();
        options.add("yes");
        options.add("no");
        StringSetInputScanner moreOptions = new StringSetInputScanner(options);
        moreOptions.setMessage("\nDo you want to continue using this account? (yes/no) ");
        moreOptions.setError("\nYou must state either \"yes\" or \"no\"");

        if (prompt.getUserInput(moreOptions).equals("no")) {
            currentAccount = null;
        }
        serve();
    }


    private void chooseAccount(Customer chooseCustomer) {

//        Integer[] custAccounts = chooseCustomer.getAccountMap().keySet().toArray(new Integer[0]);
//
//        //checks if customer has no accounts
//        if (chooseCustomer.getAccountMap().isEmpty()) {
//
//            System.out.println("\nYou must create an account first!");
//            serve();
//        }
//
//        // checks if customer has only one account
//        if (custAccounts.length == 1) {
//
//            currentAccount = manager.getAccount(custAccounts[0]);
//            return;
//        }
//
//        // if customer has more than one account...
//        String[] myAccounts = new String[custAccounts.length];
//
//        int index = 0;
//
//        for (Integer i : custAccounts) {
//
//            myAccounts[index] = i.toString();
//            index++;
//        }
//
//        stateNumOfAccounts();


        IntegerSetInputScanner accountsMenu = new IntegerSetInputScanner(currentCustomer.getAccountIds());
        accountsMenu.setMessage("Please choose which account to work with.");
        accountsMenu.setError("You must choose one of your accounts.");

        int chosenAccount = prompt.getUserInput(accountsMenu);
        currentAccount = manager.getAccount(custAccounts[chosenAccount - 1]);
    }

    private int chooseDepositAmount() {

        IntegerInputScanner getCustAmount = new IntegerInputScanner();
        getCustAmount.setMessage("\nPlease state an amount to deposit : ");
        getCustAmount.setError("\nPlease state a positive number.");

        return prompt.getUserInput(getCustAmount);
    }

    private int chooseWithdrawalAmount() {

        IntegerInputScanner getCustAmount = new IntegerInputScanner();
        getCustAmount.setMessage("\nPlease state an amount to withdraw : ");
        getCustAmount.setError("\nPlease state a positive number.");

        return prompt.getUserInput(getCustAmount);
    }


    private void stateNumOfAccounts() {

        System.out.println(currentCustomer.getTotalNumOfAccounts() > 1 ?
                "\nYou currently have " + currentCustomer.getTotalNumOfAccounts() + " accounts open at this bank." :
                "\nYou currently have " + currentCustomer.getTotalNumOfAccounts() + " account open at this bank.");
    }


    private void transfer() {

        // check for two accounts
        if (AccountManager.getNumberAccounts() < 2) {

            System.out.println("\nYou must be able to state at least two accounts!");
            return;
        }
        System.out.println("\nYou must state desired source and destinations accounts");


        // withdraw from source account
        if (currentAccount == null) {

            chooseAccount(currentCustomer);
        }

        double amount = chooseWithdrawalAmount();
        double balance = currentAccount.getBalance();
        int sourceID = currentAccount.getId();
        int destinationID;

        if (amount > balance) {
            System.out.println("\nNot enough funds.");
        }
        if (currentAccount instanceof SavingsAccount && balance - amount < 100) {
            System.out.println("\nThe balance of your savings account must never dip below 100@");
        }
        System.out.println("\nAmount to transfer: " + amount);

        //currentAccount = null;


        // check if internal or external account, if external then choose user, then choose destination acccount

        if (checkTransferType()) {

            System.out.println("You must choose the accountholder\'s account");
            chooseDestinationAccHolder();
            chooseAccount(destinationAccHolder);

        } else {
            System.out.println("Please choose your internal account to transfer.");
            destinationAccHolder = currentCustomer;
            chooseAccount(currentCustomer);
        }
        destinationID = currentAccount.getId();


        // transfer from source to destination
        manager.transfer(sourceID, destinationID, amount);
        System.out.println("\nSuccessful deposit of: " + amount + " in " + destinationAccHolder.getName() +
                "\'s account with account number " + destinationID);

        // reset variable
        currentAccount = null;

    }

    private boolean checkTransferType() {

        String[] transfers = {"Internal", "External"};
        MenuInputScanner transferTypes = new MenuInputScanner(transfers);
        transferTypes.setMessage("Please choose transfer type.");
        transferTypes.setError("You must choose a transfer type.");

        int chosenTransfer = prompt.getUserInput(transferTypes);

        if (chosenTransfer == 1) {

            return false;
        }
        return true;
    }

    private void chooseDestinationAccHolder() {

        Set<Customer> allCustomers = bank.getAllCustomers();
        String[] allUsers = new String[allCustomers.size()];
        int index = 0;

        for (Customer c : allCustomers) { //convert Customer Set to CustName String[]
            allUsers[index] = c.getName();
            index++;
        }

        MenuInputScanner chooseUser = new MenuInputScanner(allUsers);
        chooseUser.setMessage("\nPlease choose which accountholder to transfer to.");
        chooseUser.setError("You must specify an accountholder.");

        int chosenUser = prompt.getUserInput(chooseUser);

        for (Customer c : allCustomers) {

            if (c.getName().equals(allUsers[chosenUser - 1])) {

                destinationAccHolder = c;
            }
        }
    }


    private void quit() {

        currentCustomer = null;
        currentAccount = null;
        giveQuitOption();
    }

    private void giveQuitOption() {

        Set<java.lang.String> quitApp = new HashSet<>();
        quitApp.add("yes");
        quitApp.add("no");
        StringSetInputScanner quitOption = new StringSetInputScanner(quitApp);
        quitOption.setMessage("\nDo you want to shut down the application? (yes/no) ");
        quitOption.setError("You must state either \"yes\" or \"no\"");

        processQuit(prompt.getUserInput((quitOption)));
    }

    private void processQuit(String promptInput) {

        if (promptInput.equals("yes")) {

            System.exit(0);
        }

        getCustomerNumber();
    }
}
