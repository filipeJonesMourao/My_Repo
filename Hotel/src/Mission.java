public class Mission {

    public static void mission(String name, Hotel hotel) {
        String hotelName = hotel.getName();
        System.out.println("\nAgent " + name + ", here is your new mission. Your target has been located and the stake out must be carried out immediately. Details of your accommodation have already been taken care of: you must find your room at the " + hotelName);
    }
}
