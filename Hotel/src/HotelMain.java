public class HotelMain {

    public static void main(String[] args) {

        Hotel hiltonParis= new Hotel("Hilton", 500);
        Hotel ibis = new Hotel ("Ibis", 200);


        Person Filipe = new Person("Filipe");
        Person Carol = new Person("Carol");
        Person Joao = new Person("João");





        Mission.mission(Filipe.getName(), hiltonParis);

        Filipe.checkIn(hiltonParis);


        Filipe.getRoom();

        //Joao.checkIn(hiltonParis);

    }
}
