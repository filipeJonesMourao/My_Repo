public class Lamp {

    private int maxNumGenies;
    private int genieCounter;
    private int rechargeCounter;


    public Lamp(int max) {
        this.maxNumGenies = max;
    }

    public Genie rub() {
        ;
        if (maxNumGenies > genieCounter) {
            return genieCounter % 2 == 0 ? new FriendlyGenie() : new GrumpyGenie();
        } else {
            return new Demon();
        }
    }

    public void recharge() {
        Genie g = new Genie();
        g.recycle();
        rechargeCounter++;
        genieCounter = 0;
    }

    public boolean Compare(Lamp lamp) {
        int n = lamp.getMax();
        int g = lamp.getGenieCounter();
        int r = lamp.getRechargeCounter();
        boolean comparison = false;

        if (n == maxNumGenies && g == genieCounter && r == rechargeCounter) {
            comparison = true;
        }
        return comparison;
    }

    public int getMax() {
        return maxNumGenies;
    }

    public int getGenieCounter() {
        return genieCounter;
    }

    public int getRechargeCounter() {
        return rechargeCounter;
    }

}
