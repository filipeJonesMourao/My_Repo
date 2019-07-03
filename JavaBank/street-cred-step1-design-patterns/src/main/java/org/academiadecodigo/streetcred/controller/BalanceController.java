package org.academiadecodigo.streetcred.controller;


import org.academiadecodigo.streetcred.view.BalanceView;

public class BalanceController extends AbstractController{

    @Override
    public void init() {

        if (getCustomer().getAccounts().size() == 0) {
            ((BalanceView)view).noAccounts();
        }

        super.init();
    }
}
