package org.academiadecodigo.streetcred.controller.transaction;

import org.academiadecodigo.streetcred.domain.Customer;
import org.academiadecodigo.streetcred.managers.AccountManager;
import org.academiadecodigo.streetcred.view.transactionview.DepositView;

public class DepositController extends AbstractAccountTransactionController {



    @Override
    public void init() {

        if (getCustomer().getAccounts().size() == 0) {
            ((DepositView)view).noAccounts();
        }

        getCustomer();
        String accountList = buildAccountList();
        ((DepositView) view).setAccountList(accountList);

        super.init();

        Integer accountId = ((DepositView) view).getAccountID();
        Double amount = ((DepositView) view).getAmount();

        if (getCustomer().getAccountIds().contains(accountId)) {
            getAccountManager().deposit(accountId, amount);
        }
    }

    private Customer getCustomer() {
        int customerID = bank.getCurrentCustomerID();
        return bank.getCustomer(customerID);
    }

    private AccountManager getAccountManager() {
        return bank.getAccountManager();
    }








}
