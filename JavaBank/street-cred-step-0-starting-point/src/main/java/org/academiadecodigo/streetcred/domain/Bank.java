package org.academiadecodigo.streetcred.domain;

import org.academiadecodigo.streetcred.managers.AccountManager;

import java.util.HashSet;
import java.util.Set;

/**
 * The bank entity
 */
public class Bank {

    private AccountManager accountManager;
    private Set<Customer> customers = new HashSet<>();

    /**
     * Creates a new instance of Bank and initializes it with the given account manager
     *
     * @param accountManager the account manager
     */
    public Bank(AccountManager accountManager) {
        this.accountManager = accountManager;
    }

    /**
     * Adds a new customer to the bank
     *
     * @param customer the new bank customer
     * @see Customer#setAccountManager(AccountManager)
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        customer.setAccountManager(accountManager);
        customer.setCustomerNumber(customers.size());
    }

    /**
     * Gets the total balance of the bank
     *
     * @return the bank total balance
     */
    public double getBalance() {

        double balance = 0;

        for (Customer customer : customers) {
            balance += customer.getBalance();
        }

        return balance;
    }

    public int getCustomersNum() {

        return customers.size();
    }

    public Set getAllCustomers() {

        return customers;
    }

    public Customer getCustomer(int custNum) {

        Customer currentCustomer = null;

        for (Customer c :  customers) {
            if (c.getCustomerNumber() == custNum) {

                currentCustomer = c;
            }
        }

        return currentCustomer;
    }

    public AccountManager getAccountManager() {

        return this.accountManager;
    }
}
