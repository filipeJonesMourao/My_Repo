package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Wasp extends GameObjectPosition{

    private Picture wasp;
    private int x;
    private int y;

    Wasp(int x, int y, String fileName){

        this.x = x;
        this.y = y;

        setX(x);
        setY(y);

        wasp = new Picture(x,y, fileName);
        wasp.draw();

        wasp = new Picture(x,y, fileName);
        wasp.draw();
    }

    public void delete(){

        wasp.delete();
    }

    public void fly(){
        if(x < 700){

            wasp.translate(50, 0);
            x += 50;
        }
        if (x > 660) {
            wasp.translate(-50, 0);
        }
    }
}
