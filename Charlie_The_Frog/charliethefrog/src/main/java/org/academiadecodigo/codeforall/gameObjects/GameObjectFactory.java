package org.academiadecodigo.codeforall.gameObjects;

abstract public class GameObjectFactory {

    public static Pond createPond(int x, int y){

        Pond pond = new Pond();

        pond.setX(x);
        pond.setY(y);

        return pond;
    }

    public static Home createHome(int x, int y){

        Home home = new Home();

        home.setX(x);
        home.setY(y);

        return home;
    }

    public static Goal createGoal(int x, int y){

        Goal goal = new Goal();

        goal.setX(x);
        goal.setY(y);

        return goal;
    }

    public static LillyPad createLillyPad(int x, int y){

        return new LillyPad(x, y);

    }

    public static Fly createFly(int x, int y){

        Fly fly = new Fly(x,y);

        return fly;
    }

    public static BonusFly createBonusFly(int x, int y){

        BonusFly bonusFly = new BonusFly(x, y);

        return bonusFly;
    }

    public static PoisonFly createPoisonFly(int x, int y){

        PoisonFly poisonFly = new PoisonFly(x, y);

        return poisonFly;
    }

    public static Wasp createWasp(int x, int y, String fileName){

        Wasp wasp = new Wasp(x,y, fileName);

        wasp.setX(x);
        wasp.setY(y);

        return wasp;
    }

}
