package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Home extends GameObjectPosition {

    //private Rectangle home;

    private Picture home;

    Home(){

        /*setX(10);
        setY(560);*/

        home = new Picture(10,560, "Home.png");
        home.draw();

        /*home = new Rectangle(10, 510, 800, 90);
        home.setColor(Color.DARK_GRAY);
        home.fill();*/
    }
}
