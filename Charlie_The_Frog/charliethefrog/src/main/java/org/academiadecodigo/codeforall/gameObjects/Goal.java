package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.pictures.Picture;

import java.awt.*;

public class Goal extends GameObjectPosition {

    //private Ellipse goal;

    private Picture goal;
    private  Picture whereMyFly = new Picture(10,410, "Where my fly.png");

    /*private int xLocation(){

        x = (int) ((Math.random() * 665) + 30);

        return x;
    }*/


    Goal(){

        /*setY(20);
        setX(340);*/

        goal = new Picture(340,20,"Rock w female.png");
        goal.draw();

        /*goal = new Ellipse(x, y, 90, 90);
        goal.setColor(Color.DARK_GRAY);
        goal.fill();*/
    }

    public void delete(){

        goal.delete();
    }

    public void noJoy(){

        whereMyFly.draw();
    }

    public void deleteMessage(){

        whereMyFly.delete();
    }

}
