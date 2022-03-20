import java.util.ArrayList;

public class HotelList {
    private static HotelList hotelList;
    private ArrayList<Hotel> hotels;

    private HotelList(){

    }

    public static HotelList getInstance(){
        if (hotelList == null) hotelList = new HotelList();
        return hotelList;
    }

    public Hotel getHotel(int id){
        return null;
    }
    
}
