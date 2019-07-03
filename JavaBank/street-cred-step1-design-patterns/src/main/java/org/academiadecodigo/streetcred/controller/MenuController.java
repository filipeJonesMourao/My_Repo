package org.academiadecodigo.streetcred.controller;

import org.academiadecodigo.streetcred.Messages;
import org.academiadecodigo.streetcred.UserOptions;
import org.academiadecodigo.streetcred.view.MenuView;
import org.academiadecodigo.streetcred.view.View;

import java.util.Map;

public class MenuController extends AbstractController {

    private Map<Integer, Controller> controllerMap;


    public void setControllerMap(Map<Integer, Controller> controllerMap) {
        this.controllerMap = controllerMap;
    }


    @Override
    public void setView(View view) {
        super.setView(view);
    }

    public void selectOption(int userChoice) {

        if (userChoice == UserOptions.QUIT.getOption()) {
            return;
        }

        controllerMap.get(userChoice).init();

        init();
    }


}
