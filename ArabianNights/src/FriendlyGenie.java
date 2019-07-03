public class FriendlyGenie extends Genie {

    public FriendlyGenie(){
        super();

    }

   @Override
   public void grantWish(){
       if (maxWish < wishCount){
           System.out.println("You have no more wishes, sorry");
       } else {
           super.grantWish();
           System.out.println("Why hello there, I'm your Genie for this trip. How should I address you?");
           System.out.println("So, whats it gonna be? Your wish is my command! har har har");
       }

   }
}
