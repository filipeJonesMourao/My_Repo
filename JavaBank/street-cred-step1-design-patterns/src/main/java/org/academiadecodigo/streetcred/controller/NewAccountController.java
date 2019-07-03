package org.academiadecodigo.streetcred.controller;

import org.academiadecodigo.streetcred.view.NewAccountView;
import org.academiadecodigo.streetcred.domain.Customer;
import org.academiadecodigo.streetcred.domain.account.AccountType;

public class NewAccountController extends AbstractController {


    @Override
    public void init() {

        int newAccountID = getCustomer().openAccount(AccountType.CHECKING);
        ((NewAccountView) view).setNewAccountId(newAccountID);

        super.init();
    }

    protected Customer getCustomer() {
        int customerID = bank.getCurrentCustomerID();
        return bank.getCustomer(customerID);
    }


}
