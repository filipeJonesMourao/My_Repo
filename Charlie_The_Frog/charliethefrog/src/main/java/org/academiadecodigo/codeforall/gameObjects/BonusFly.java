package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.simplegraphics.pictures.Picture;

import org.academiadecodigo.codeforall.Sound;

public class BonusFly extends SuperFlies{

    private Picture bonusFly;
    private boolean alive;
    private Sound sound;

    BonusFly(int x, int y){

        alive = true;

        setX(x);
        setY(y);

        bonusFly = new Picture(x,y,"Bonus Fly.png");
        draw();
    }

    public boolean isAlive(){
        return alive;
    }

    public void die() {
        alive = false;

        sound = new Sound("/Sounds/Golden Fly Short.wav");

        sound.play(true);
    }

    public void draw(){

        bonusFly.draw();
    }

    public void delete(){

        bonusFly.delete();
    }
}
