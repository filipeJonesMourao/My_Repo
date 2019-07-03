package org.academiadecodigo.streetcred.controller;

import org.academiadecodigo.streetcred.domain.Customer;
import org.academiadecodigo.streetcred.view.View;
import org.academiadecodigo.streetcred.domain.Bank;

public abstract class AbstractController implements Controller {

    protected Bank bank;
    protected View view;


    public void init() {
        view.show();
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    public void setView(View view) {
        this.view = view;
    }

    @Override
    public boolean hasAccounts() {
        return getCustomer().getAccountIds().size() > 0;
    }

    Customer getCustomer() {
        int customerID = bank.getCurrentCustomerID();
        return bank.getCustomer(customerID);
    }





}
