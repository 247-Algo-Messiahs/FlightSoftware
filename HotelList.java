import java.util.ArrayList;

public class HotelList {
    private static HotelList hotelList;
    private ArrayList<Hotel> hotels;

    private HotelList(){

    }

    public HotelList getInstance(){
        if (this.hotelList == null) this.hotelList = new HotelList();
        return this.hotelList;
    }

    public Hotel getHotel(int id){
        return null;
    }
    
}
