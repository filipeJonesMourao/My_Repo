package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Pond extends GameObjectPosition {

    private Picture pond;

    Pond(){

        /*setX(10);
        setY(10);*/

        pond = new Picture(10,10, "Water.png");
        pond.draw();

        /*river = new Rectangle(10, 10, 800, 500);
        river.setColor(Color.BLUE);
        river.fill();*/
    }

    public void delete() {
        pond.delete();
    }


}
