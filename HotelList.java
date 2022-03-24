import java.util.ArrayList;
import java.util.UUID;

public class HotelList {
    private static HotelList hotelList;
    private static ArrayList<Hotel> hotels;

    private HotelList(){
        hotels = DataLoader.loadHotels();
    }

    public static HotelList getInstance(){
        if (hotelList == null) hotelList = new HotelList();
        return hotelList;
    }

    public ArrayList<Hotel> getHotels(){
        return hotels;
    }

    public void setHotels(ArrayList<Hotel> hotelsArrayList) {
        hotels = hotelsArrayList;
    }

    public static Hotel getHotelByUUID(UUID uuid) {
        for (int i = 0; i < hotels.size(); i++) {
            Hotel selectedHotel = hotels.get(i);
            if (selectedHotel.getHotelID().equals(uuid)) return selectedHotel;
        }
        return null;
    }
    
}
