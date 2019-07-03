package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class LillyPad extends GameObjectPosition {

    //private Ellipse lillyPad;

    private Picture lillyPad;

    LillyPad(int x, int y){

        setX(x);
        setY(y);

        lillyPad = new Picture(x,y,"LillyPad.png");
        lillyPad.draw();

        /*lillyPad = new Ellipse(x, y, 50,50);
        lillyPad.setColor(Color.GREEN);
        lillyPad.fill();*/
    }

    public void delete() {

        lillyPad.delete();
    }
}
