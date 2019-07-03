package org.academiadecodigo.streetcred;

import org.academiadecodigo.streetcred.domain.Bank;
import org.academiadecodigo.streetcred.domain.Customer;
import org.academiadecodigo.streetcred.managers.AccountManager;

public class Main {

    public static void main(String[] args) {

        AccountManager accManager = new AccountManager();
        Bank jBank = new Bank(accManager);
        StreetCred streetCred = new StreetCred(jBank);

        //Create customers here
        Customer ColonelCuster = new Customer();
        Customer MrsCustard = new Customer();
        Customer MasterCostumeMaker = new Customer();


        //Don't forget to add customers to Bank
        jBank.addCustomer(ColonelCuster);
        ColonelCuster.setName("ColonelCuster");
        jBank.addCustomer(MrsCustard);
        MrsCustard.setName("MrsCustard");
        jBank.addCustomer(MasterCostumeMaker);
        MasterCostumeMaker.setName("MasterCostumeMaker");

        streetCred.open();
    }
}
