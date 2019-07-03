public class Hotel {


    // Properties
    private String hotelName;
    //private int numOfRooms = 1;
    private boolean availability = false;
    private Room[] rooms;
    private Room attributedRoom;
    //private Room[] groupAttributedRooms = new Room[numOfRooms];



    // Constructor

    public Hotel(String hotelName, int numOfRooms){
        this.hotelName = hotelName;
        //this.numOfRooms = numOfRooms;
        rooms = new Room[numOfRooms];

        for (int i = 1; i <= numOfRooms; i++){
            Room room = new Room(i);
            rooms[i-1] = room;
            room.setRoomNum(i);
        }
    }

    // Methods

    private boolean checkRooms(){
        int numFreeRooms = 0;
        boolean availability = false;
        for(Room room : rooms){
            if(room.isOccupied() == false){
                numFreeRooms++;
                availability = true;
                System.out.println("\nWelcome to the "+hotelName+". Ah, here we are. We have a room for you.");
                break;
            }
        }
        return availability;
    }



    private Room giveKey(){
        for(Room room : rooms){
            if(room.isOccupied() == false){
                attributedRoom = room;
                System.out.println("You have been attributed room "+room.getRoomNum());
                break;
            }
        }
        return attributedRoom;
    }


    public void checkIn(){
        if(checkRooms() == true) {
            giveKey();
            attributedRoom.changeRoomState();
        }
    }


    public void checkOut(int num){
        Room room = rooms[num-1];
        room.changeRoomState();
        System.out.println("\nWe hope you have enjoyed your stay.");
}


    //Getters & Setters
    public Room getRoom(){
        return attributedRoom;
    }

    public String getName() {
        return hotelName;
    }
}
