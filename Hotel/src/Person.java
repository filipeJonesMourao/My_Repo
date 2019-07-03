public class Person {

    // Properties
    private String name;
    private Hotel hotel;
    private int checkInCounter = 0;


    // Constructor
    public Person(String name){
        this.name = name;
    }


    // Methods
    public void checkIn(Hotel hotel){
        if (checkInCounter > 0){
            System.out.println("\nExcuse me Sir/Madam, it seems you are already checked in at a hotel. I\'m afraid you must check out before checking in again.");
        }
        else {
            setHotel(hotel);
            hotel.checkIn();
            checkInCounter++;
        }

    }

    /*public void groupCheckIn(int groupNumber, Hotel name){
        name.checkIn(groupNumber);
    }*/

    public void checkOut(Hotel name, int roomNum){
        name.checkOut(roomNum);
        checkInCounter--;
        resetHotel();

    }

    /* public void groupCheckOut(Hotel name, Room[] room){
        name.groupCheckOut(room);
    }*/


    // Getters & Setters

    public String getName(){
        return this.name;
    }

    public void getRoom(){
        hotel.getRoom();
    }

    private Hotel getHotel(){
        return hotel;
    }

    public void setHotel(Hotel hotel){
        this.hotel = hotel;
    }

    public void resetHotel(){
        this.hotel = null;
    }
}
