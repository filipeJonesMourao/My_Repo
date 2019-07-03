package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.codeforall.Sound;


public class PoisonFly extends SuperFlies{

    private Picture poisonFly;
    private boolean alive;
    private Sound sound;


    PoisonFly(int x, int y){

        alive = true;
        setX(x);
        setY(y);
        poisonFly = new Picture(x,y,"Poisonous Fly.png");
        poisonFly.draw();
    }

    public boolean isAlive(){
        return alive;
    }

    public void delete(){

        poisonFly.delete();
    }
    public void die(){
        alive = false;

        sound = new Sound("/Sounds/Eat Poison.wav");
        sound.play(true);
    }
}
