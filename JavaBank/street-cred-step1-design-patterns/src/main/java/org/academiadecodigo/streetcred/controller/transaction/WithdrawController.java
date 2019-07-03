package org.academiadecodigo.streetcred.controller.transaction;

import org.academiadecodigo.streetcred.controller.AbstractController;
import org.academiadecodigo.streetcred.domain.Customer;
import org.academiadecodigo.streetcred.managers.AccountManager;
import org.academiadecodigo.streetcred.view.transactionview.DepositView;
import org.academiadecodigo.streetcred.view.transactionview.WithdrawView;

public class WithdrawController extends AbstractAccountTransactionController {

    @Override
    public void init() {

        if (getCustomer().getAccounts().size() == 0) {
            ((WithdrawView)view).noAccounts();
        }

        getCustomer();
        String accountList = buildAccountList();
        ((WithdrawView) view).setAccountList(accountList);

        super.init();

        Integer accountId = ((WithdrawView) view).getAccountID();
        Double amount = ((WithdrawView) view).getAmount();

        if (getCustomer().getAccountIds().contains(accountId)) {
            getAccountManager().withdraw(accountId, amount);
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
