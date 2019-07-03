public class MagicLampMain {

    public static void main(String[] args) {

        Lamp l1 = new Lamp(5);
        Lamp l2 = new Lamp (3);
        Genie g = l1.rub();


        l1.rub();
        System.out.println(l1.getMax());
        System.out.println(l1.getGenieCounter());

        g.grantWish();
        System.out.println(g.getWishCount());

        l1.rub();
        System.out.println(g.getWishCount());

        l1.recharge();

        System.out.println("\n");
        System.out.println(l1.getRechargeCounter());
        System.out.println("\n");
        System.out.println(l1.getGenieCounter());

    }
}


