public class Room {


    // Properties

    private int roomNum;
    private boolean occupied = false;
    private boolean doorLock = true;
    private String nameOfOccupant;


    // Constructor

    public Room(int roomNum){
        this.roomNum = roomNum+1;
    }


    // Methods

    public boolean changeRoomState(){
        return occupied != occupied;
    }


    public boolean isOccupied(){
        return occupied;
    }

    // Getters & Setters
    public int getRoomNum(){
        return this.roomNum;
    }

    public int setRoomNum(int num){
        this.roomNum = num;
        return this.roomNum;
    }


    public String setRoomName(String name){
        this.nameOfOccupant = name;
        return this.nameOfOccupant;
    }
}

