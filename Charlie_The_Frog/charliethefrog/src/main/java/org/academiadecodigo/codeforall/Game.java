package org.academiadecodigo.codeforall;

import org.academiadecodigo.codeforall.gameObjects.*;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.pictures.Picture;

public class Game implements KeyboardHandler {

    private Frog charlie;
    private Pond pond;
    private Home home;
    private Goal goal;
    private Wasp[] wasp;
    private LillyPad[] lillyPads;
    private Fly[] flies;
    private BonusFly bonusFly;
    private PoisonFly[] poisonFlies;

    private Keyboard keyboard;
    private int delay;

    private boolean isStarted;
    private boolean isGameOver;
    private boolean toStart;

    private Picture startLogo;
    private Picture gameOver;
    private Picture happyEnding;
    private Picture[] redHeart;
    private Picture[] grayHeart;
    private Sound sound;

    private int flyCounter;


    public Game() {

        isStarted = false;
        isGameOver = false;
        keyboard = new Keyboard(this);
        this.delay = 200;
    }

    public void init() {

        startLogo = new Picture(10, 10, "Logo1.png");
        startLogo.draw();

        KeyboardEvent start = new KeyboardEvent();
        start.setKey(KeyboardEvent.KEY_SPACE);
        start.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent left = new KeyboardEvent();
        left.setKey(KeyboardEvent.KEY_LEFT);
        left.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent right = new KeyboardEvent();
        right.setKey(KeyboardEvent.KEY_RIGHT);
        right.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent up = new KeyboardEvent();
        up.setKey(KeyboardEvent.KEY_UP);
        up.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        KeyboardEvent down = new KeyboardEvent();
        down.setKey(KeyboardEvent.KEY_DOWN);
        down.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);

        keyboard.addEventListener(start);
        keyboard.addEventListener(left);
        keyboard.addEventListener(right);
        keyboard.addEventListener(up);
        keyboard.addEventListener(down);
    }

    public void start() {

        isStarted = true;
        isGameOver = false;
        startLogo.delete();

        flyCounter = 0;
        createPond();
        createGoal();
        createHome();
        createLilliPads();
        createHearts();
        //createWasp();



        charlie = new Frog();
        //SimpleGfxGrid grid = new SimpleGfxGrid();
    }

/*    public void start() {

        while (toStart && !isStarted) {

            isStarted = true;
            isGameOver = false;
            startLogo.delete();

            flyCounter = 0;
            createPond();
            createGoal();
            createHome();
            createLilliPads();

            charlie = new Frog();
            //SimpleGfxGrid grid = new SimpleGfxGrid();

            charlie.hop();
            checkPos();
            goal.deleteMessage();
        }
    }*/

    private void createLilliPads() {

        int x = 60;
        int y = 160;
        int fliesIndex = 0;
        int poisonIndex = 0;

        lillyPads = new LillyPad[28];
        flies = new Fly[28];
        poisonFlies = new PoisonFly[28];

        for (int i = 0; i < lillyPads.length; i++) {

            if (x > 695) {

                x = 60;
                y += 100;
            }

            if (Math.random() < 0.6) {

                lillyPads[i] = GameObjectFactory.createLillyPad(x, y);

            } else {

                if (Math.random() <= 0.5) {
                    lillyPads[i] = GameObjectFactory.createLillyPad(x, y);
                    flies[fliesIndex] = GameObjectFactory.createFly(x, y);
                    fliesIndex++;
                    flyCounter++;
                } else {
                    lillyPads[i] = GameObjectFactory.createLillyPad(x, y);
                    poisonFlies[poisonIndex] = GameObjectFactory.createPoisonFly(x, y);
                    poisonIndex++;
                }

            }

            x += 100;
        }
    }

    private void createHearts() {
        redHeart = new Picture[3];
        grayHeart = new Picture[3];
        int x = 15;
        int y = 15;
        for (int i = 0; i < redHeart.length; i++) {

            grayHeart[i] = new Picture(x, y, "Gray Heart.png");
            grayHeart[i].draw();
            redHeart[i] = new Picture(x, y, "Red Heart.png");
            redHeart[i].draw();

            x += 35;
        }
    }

    private void createPond() {

        pond = GameObjectFactory.createPond(10, 10);
    }

    private void createGoal() {

        goal = GameObjectFactory.createGoal(20, 340);
    }

    private void createHome() {

        home = GameObjectFactory.createHome(10, 560);
    }

    public void checkPos() {

        if (charlie.getY() < 111 && (charlie.getX() < 340 || charlie.getX() > 430)) { // when frog falls in river

            charlie.die();
            gameOver();
        }

        if (charlie.getY() <= 560 && charlie.getY() > 50) { // when frog eats flies, poison flies and golden fly
            int i = 0;

            while (flies[i] != null) {

                if (charlie.getX() == flies[i].getX() && charlie.getY() == flies[i].getY() && flyCounter > 0) {

                    if (!flies[i].isAlive()) {
                    }

                    if (flies[i].isAlive()) {

                        flies[i].delete();
                        flies[i].die();

                        flyCounter--;
                    }


                    if (flyCounter == 0) {
                        createBonusFly();
                    }
                }

                i++;
            }

            i = 0;
            while (poisonFlies[i] != null) {

                if (charlie.getX() == poisonFlies[i].getX() && charlie.getY() == poisonFlies[i].getY()) {

                    if (poisonFlies[i].isAlive()) {

                        poisonFlies[i].delete();
                        poisonFlies[i].die();
                        charlie.loseLife();
                        redHeart[charlie.getLives()].delete();
                    }

                    if (charlie.getLives() == 0) {

                        charlie.die();
                        gameOver();
                    }
                }

                i++;
            }

            if (flyCounter == 0) {

                if (charlie.getX() == bonusFly.getX() && charlie.getY() == bonusFly.getY()) { //
                    if (bonusFly.isAlive()) {
                        bonusFly.delete();
                        bonusFly.die();
                    }
                }
            }
        }

        if (charlie.getY() < 100 && charlie.getX() > 340 && charlie.getX() < 430) { // charlie arrives at she-frog

            if (flyCounter > 0 || bonusFly.isAlive()) {

                goal.noJoy();
                return;
            }

            charlie.happyEnding();

            gameOver();
        }
    }

    private void createBonusFly() {

        if (flyCounter == 0) {

            int random = (int) (Math.random() * lillyPads.length);

            int x = lillyPads[random].getX();
            int y = 560;

            bonusFly = GameObjectFactory.createBonusFly(x, y);
        }
    }

    private void createWasp() {

        int x = 10;
        int y = 160;
        wasp = new Wasp[8];
        for(int i = 0; i < wasp.length/2; i++){

            wasp[i] = GameObjectFactory.createWasp(x, y, "Wasp.png");
            y += 100;

        }
        x = 710;
        y = 160;
        for(int i = wasp.length/2; i < wasp.length; i++){

            wasp[i] = GameObjectFactory.createWasp(x, y, "WaspLeft.png");
            y += 100;

        }


    }

    private void gameOver() {

        if (!charlie.isAlive()) {

            gameOver = new Picture(10, 10, "Game Over.png");
            gameOver.draw();
            sound = new Sound("/Sounds/Hero Death.wav");

            sound.play(true);
            isGameOver = true;
        }

        if (charlie.isHappy()) {

            happyEnding = new Picture(10, 10, "Happy Ending.png");
            happyEnding.draw();
        }

        isStarted = false;
    }

    private void restart() {

        if (charlie.isHappy()) {
            happyEnding.delete();
        }

        if (isGameOver) {

            gameOver.delete();
        }


        for (LillyPad lillyPad : lillyPads) {
            lillyPad.delete();
        }

        for (Fly fly : flies) {

            if (fly != null) {

                fly.delete();
            }
        }

        for(PoisonFly poisonFly : poisonFlies){
            if(poisonFly != null){
                poisonFly.delete();
            }
        }

        //isGameOver = false;
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {

        if (keyboardEvent.getKey() == KeyboardEvent.KEY_SPACE && !isStarted) {


            if (isGameOver) {

                restart();
            }

            start();
        }

        if (isStarted) {
            switch (keyboardEvent.getKey()) {

                case KeyboardEvent.KEY_LEFT:
                    charlie.setDirection(Direction.LEFT);
                    charlie.hop();
                    checkPos();
                    goal.deleteMessage();
                    break;

                case KeyboardEvent.KEY_RIGHT:
                    charlie.setDirection(Direction.RIGHT);
                    charlie.hop();
                    checkPos();
                    goal.deleteMessage();
                    break;

                case KeyboardEvent.KEY_UP:
                    charlie.setDirection(Direction.UP);
                    charlie.hop();
                    checkPos();
                    break;

                case KeyboardEvent.KEY_DOWN:
                    charlie.setDirection(Direction.DOWN);
                    charlie.hop();
                    checkPos();
                    goal.deleteMessage();
                    break;
            }
        }
        /*charlie.hop();
        checkPos();
        goal.deleteMessage();*/
    }


    @Override
    public void keyReleased(KeyboardEvent event) {
    }
}
