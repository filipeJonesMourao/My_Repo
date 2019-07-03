package org.academiadecodigo.streetcred.domain;

import org.academiadecodigo.streetcred.domain.account.Account;
import org.academiadecodigo.streetcred.domain.account.AccountType;
import org.academiadecodigo.streetcred.managers.AccountManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * The customer domain entity
 */
public class Customer {

    private AccountManager accountManager;
    private Map<Integer, Account> accounts = new HashMap<>();
    private int customerNumber;
    private String customerName;

    /**
     * Sets the account manager
     *
     * @param accountManager the account manager to set
     */
    public void setAccountManager(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Opens a new account
     *
     * @param accountType the account type to be opened
     * @return the new account id
     * @see AccountManager#openAccount(AccountType)
     */
    public int openAccount(AccountType accountType) {
        Account account = accountManager.openAccount(accountType);
        accounts.put(account.getId(), account);
        return account.getId();
    }

    /**
     * Gets the balance of an {@link Account}
     *
     * @param id the id of the account
     * @return the account balance
     */
    public double getBalance(int id) {
        return accounts.get(id).getBalance();
    }

    /**
     * Gets the total customer balance
     *
     * @return the customer balance
     */
    public double getBalance() {

        double balance = 0;

        for (Account account : accounts.values()) {
            balance += account.getBalance();
        }

        return balance;
    }

    public void setCustomerNumber(int custNum){

        this.customerNumber = custNum;
    }

    public int getCustomerNumber() {

        return this.customerNumber;
    }

    public int getTotalNumOfAccounts() {

        return accounts.size();
    }

    public Map<Integer, Account> getAccountMap() {

        return this.accounts;
    }

    public void setName(String custName) {

        this.customerName = custName;
    }

    public String getName() {

        return customerName;
    }

}
