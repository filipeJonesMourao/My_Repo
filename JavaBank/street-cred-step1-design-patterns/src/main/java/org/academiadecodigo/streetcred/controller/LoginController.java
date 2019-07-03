package org.academiadecodigo.streetcred.controller;

import org.academiadecodigo.streetcred.view.View;

public class LoginController extends AbstractController {


    private int customerID;

    private MenuController menuController;

    @Override
    public void init() {
        super.init();
        bank.setCurrentCustomerID(customerID);

        menuController.init();
    }

    @Override
    public void setView(View view) {
        super.setView(view);
    }


    public void setChoice(double choice) {
        this.customerID = (int) choice;
    }

    public void setMenuController(MenuController menuController) {
        this.menuController = menuController;
    }
}
