public class Groups {

    // Properties
    String hotelName;
    private int numOfRooms = 1;
    private boolean availability = false;
    Room[] rooms;
    private Room attributedRoom;
    private Room[] groupAttributedRooms = new Room[numOfRooms];



    private boolean checkRooms(int num){ // GROUP METHOD
        int numFreeRooms = 0;
        for(Room room : rooms){
            if(room.isOccupied() == false){
                numFreeRooms++;
            }
        }
        if (numFreeRooms >= num){
            availability = true;
            System.out.println("We\'re in luck! There are enough rooms for all of you!");
        }
        return availability;
    }


    private Room[] giveKey(int num){ // GROUP METHOD
        for(int x = 0; x < num; x++){
            for(Room room : rooms){
                if(room.isOccupied() == false){
                    groupAttributedRooms[x] = room;
                    break;
                }
            }
        }
        System.out.println("You have been attributed the following rooms: "+groupAttributedRooms);
        return groupAttributedRooms;
    }


    public void checkIn(int num){ // GROUP METHOD
        if(checkRooms(num) == true) {
            giveKey(num);
            for (Room room : groupAttributedRooms){
                room.changeRoomState();
            }
        }
    }


    public void groupCheckOut(Room[] groupRooms){  // GROUP METHOD
        for (Room room : groupAttributedRooms){
            room.changeRoomState();
        }
    }



}
