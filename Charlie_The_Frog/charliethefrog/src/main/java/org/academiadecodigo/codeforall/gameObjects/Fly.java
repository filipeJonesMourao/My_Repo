package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import org.academiadecodigo.codeforall.Sound;

public class Fly extends SuperFlies{

    private Picture fly;
    private boolean alive;
    private Sound sound;

    Fly(int x, int y){

        alive = true;
        setX(x);
        setY(y);
        fly = new Picture(x,y,"Fly.png");
        fly.draw();
    }

    public boolean isAlive(){
        return alive;
    }

    public void die(){
        alive = false;
        sound = new Sound("/Sounds/Eat Fly.wav");

        sound.play(true);
    }

    public void delete(){

        fly.delete();
    }
}
