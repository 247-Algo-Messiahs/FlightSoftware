import java.util.ArrayList;
import java.util.UUID;

public class HotelList {
    private static HotelList hotelList;
    private ArrayList<Hotel> hotels;

    private HotelList(){
        this.hotels = new ArrayList<Hotel>();
    }

    public static HotelList getInstance(){
        if (hotelList == null) hotelList = new HotelList();
        return hotelList;
    }

    public ArrayList<Hotel> getHotels(){
        return this.hotels;
    }

    public void setHotels(ArrayList<Hotel> hotels) {
        this.hotels = hotels;
    }

    public Hotel getHotelByUUID(UUID uuid) {
        for (int i = 0; i < this.hotels.size(); i++) {
            Hotel selectedHotel = this.hotels.get(i);
            if (selectedHotel.getHotelID().equals(uuid)) return selectedHotel;
        }
        return null;
    }
    
}
