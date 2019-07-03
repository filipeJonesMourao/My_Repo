public class GrumpyGenie extends Genie {

    public GrumpyGenie(){
        super();
    }

    @Override
    public void grantWish(){
        if (wishCount > 0){
            System.out.println("No more wishes for you!");
        } else {
            super.grantWish();
            System.out.println("Who dares disturb my slumber!\nI-it is I... Achmed, Achmed of Lugura\n");
            System.out.println("You have one wish: choose wisely");
        }

    }
}
