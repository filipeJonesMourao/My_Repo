package org.academiadecodigo.streetcred;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.streetcred.controller.*;
import org.academiadecodigo.streetcred.controller.transaction.DepositController;
import org.academiadecodigo.streetcred.controller.transaction.WithdrawController;
import org.academiadecodigo.streetcred.view.*;
import org.academiadecodigo.streetcred.domain.Bank;
import org.academiadecodigo.streetcred.domain.Customer;
import org.academiadecodigo.streetcred.managers.AccountManager;
import org.academiadecodigo.streetcred.view.transactionview.DepositView;
import org.academiadecodigo.streetcred.view.transactionview.WithdrawView;

import java.util.HashMap;
import java.util.Map;

public class App {

    public static void main(String[] args) {


        Bank bank = new Bank();
        AccountManager accountManager = new AccountManager();
        bank.setAccountManager(accountManager);

        Controller controller;
        View view;


        Customer c1 = new Customer(1, "Toffee");
        Customer c2 = new Customer(2, "Maggy");
        Customer c3 = new Customer(3, "Bonny");
        Customer c4 = new Customer(4, "Lucky");
        bank.addCustomer(c1);
        bank.addCustomer(c2);
        bank.addCustomer(c3);
        bank.addCustomer(c4);


        wire(bank);

    }

    private static void wire(Bank bank) {

        Prompt prompt = new Prompt(System.in, System.out);

        // wiring Login Controller
        LoginController loginController = new LoginController();
        LoginView loginView = new LoginView();
        loginController.setView(loginView);
        loginController.setBank(bank);
        loginView.setController(loginController);
        loginView.setBank(bank);
        loginView.setPrompt(prompt);

        // wiring Menu Controller
        MenuController menuController = new MenuController();
        MenuView menuView = new MenuView();
        menuController.setView(menuView);
        menuController.setBank(bank);
        menuView.setController(menuController);
        menuView.setBank(bank);
        menuView.setPrompt(prompt);
        loginController.setMenuController(menuController);

        // wiring Balance Controller
        BalanceController balanceController = new BalanceController();
        BalanceView balanceView = new BalanceView();
        balanceController.setView(balanceView);
        balanceController.setBank(bank);
        balanceView.setController(balanceController);
        balanceView.setBank(bank);
        balanceView.setPrompt(prompt);

        // wiring Deposit Controller
        DepositController depositController = new DepositController();
        DepositView depositView = new DepositView();
        depositController.setView(depositView);
        depositController.setBank(bank);
        depositView.setController(depositController);
        depositView.setBank(bank);
        depositView.setPrompt(prompt);

        // wiring Withdraw Controller
        WithdrawController withdrawController = new WithdrawController();
        WithdrawView withdrawView = new WithdrawView();
        withdrawController.setView(withdrawView);
        withdrawController.setBank(bank);
        withdrawView.setController(withdrawController);
        withdrawView.setBank(bank);
        withdrawView.setPrompt(prompt);

        // wiring NewAccount Controller
        NewAccountController newAccountController = new NewAccountController();
        NewAccountView newAccountView = new NewAccountView();
        newAccountController.setView(newAccountView);
        newAccountController.setBank(bank);
        newAccountView.setController(newAccountController);
        newAccountView.setBank(bank);
        newAccountView.setPrompt(prompt);

        QuitController quitController = new QuitController();

        Map<Integer, Controller> map = new HashMap<>();
        map.put(UserOptions.GET_BALANCE.getOption(), balanceController);
        map.put(UserOptions.DEPOSIT.getOption(), depositController);
        map.put(UserOptions.WITHDRAW.getOption(), withdrawController);
        map.put(UserOptions.OPEN_ACCOUNT.getOption(), newAccountController);
        map.put(UserOptions.QUIT.getOption(), quitController);
        // wiring Quit Controller

        menuController.setControllerMap(map);

        loginController.init();

    }
}
