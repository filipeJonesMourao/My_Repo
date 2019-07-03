package org.academiadecodigo.streetcred.controller.transaction;

import org.academiadecodigo.streetcred.controller.AbstractController;
import org.academiadecodigo.streetcred.domain.Customer;
import org.academiadecodigo.streetcred.view.BalanceView;

public abstract class AbstractAccountTransactionController extends AbstractController {


    protected String buildAccountList() {

        StringBuilder builder = new StringBuilder();

        for (Integer id : getCustomer().getAccountIds()) {
            builder.append(id);
            builder.append(" ");
        }

        return builder.toString();
    }

    private Customer getCustomer() {
        int customerID = bank.getCurrentCustomerID();
        return bank.getCustomer(customerID);
    }



}
