package org.academiadecodigo.codeforall.gameObjects;

import org.academiadecodigo.codeforall.Sound;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Frog {

    private boolean alive;
    private boolean win;

    private int lives;
    private boolean caughtBonusFly;

    private Direction direction;

    private int x;
    private int y;

    private Picture frog;
    private Sound winSound;

    //private Rectangle frog = new Rectangle(x, y, 30, 30);

    /*private int xLocation(){

        x = (int) ((Math.random() * 690) + 25);

        return x;
    }*/

    public Frog(){

        /*frog.setColor(Color.RED);
        frog.fill();
*/
        this.lives = 3;

        this.x = 360;
        this.y = 560;
        frog = new Picture(x,y, "Charlie.png");
        frog.draw();

        alive = true;
        win = false;
    }

    /*public void checkPos(){

        if (y < 111) {

            if (x < 339 || x > 431) {

                die();
                return;
            }

            happyEnding();
        }
    }*/

    public void hop(){

        switch (direction){

            case UP:
                if(y - 100 < 10){
                    return;
                }
                frog.translate(0, -100);
                y -= 100;
                break;
            case DOWN:
                if(y + 100 > 600){
                    return;
                }
                frog.translate(0, 100);
                y += 100;
                break;
            case RIGHT:
                if(x + 100 > 750){
                    return;
                }
                frog.translate(100, 0);
                x += 100;
                break;
            case LEFT:
                if(x - 100 < 10){
                    return;
                }
                frog.translate(-100, 0);
                x -= 100;
                break;
            default:
                break;
        }
        winSound = new Sound("/Sounds/Frog Jump.wav");

        winSound.play(true);

    }

    public Direction getDirection(){
        return direction;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setDirection(Direction direction){
        this.direction = direction;
    }

    public boolean isAlive(){
        return alive;
    }

    public void caughtBonusFly(){

        caughtBonusFly = true;
    }

    public boolean didCatchBonusFly(){

        return caughtBonusFly;
    }

    public int getLives(){
        return lives;
    }

    public void gainLife() {
        lives++;
    }

    public void loseLife(){

        lives--;
    }

    public void die(){

        lives = 0;
        alive = false;
    }

    public void revive(){

        alive = true;
    }

    public void happyEnding() {

        win = true;

        winSound = new Sound("/Sounds/Sex.wav");

        winSound.play(false);
    }


    public boolean isHappy() {
        return win;
    }


    public void delete() {
        frog.delete();
    }
}
