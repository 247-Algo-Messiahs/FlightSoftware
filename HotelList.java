import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * @author Nicolas Becker, Peyton Tucker, Avery Slomnicki, Austin Hanson
 */
public class HotelList {
    private static HotelList hotelList;
    private static ArrayList<Hotel> hotels;

    /**
     * Defines a HotelList object and loads hotels from database
     */
    private HotelList(){
        hotels = DataLoader.loadHotels();
    }

    /**
     * 
     * @return HotelList instance
     */
    public static HotelList getInstance(){
        if (hotelList == null) hotelList = new HotelList();
        return hotelList;
    }

    /**
     * @return ArrayList of Hotel
     */
    public static ArrayList<Hotel> getHotels(){
        return hotels;
    }

    /**
     * Sets list of hotels for given instance
     * @param hotelsArrayList
     */
    public void setHotels(ArrayList<Hotel> hotelsArrayList) {
        hotels = hotelsArrayList;
    }

    /**
     * 
     * @param uuid
     * @return Hotel with given UUID
     */
    public static Hotel getHotelByUUID(UUID uuid) {
        for (int i = 0; i < hotels.size(); i++) {
            Hotel selectedHotel = hotels.get(i);
            if (selectedHotel.getHotelID().equals(uuid)) return selectedHotel;
        }
        return null;
    }
    
    /**
     * 
     * @param airportCode
     * @return ArrayList of Hotel with given airportCode
     */
    public static ArrayList<Hotel> getHotelsByAirportCode(String airportCode) {
        ArrayList<Hotel> hotelsWithMatchingAirportCode = new ArrayList<Hotel>();

        for (int i = 0; i < hotels.size(); i++) {
            Hotel selectedHotel = hotels.get(i);
            if (selectedHotel.getAirportCode().equals(airportCode)) hotelsWithMatchingAirportCode.add(selectedHotel);
        }
        return hotelsWithMatchingAirportCode;
    }

    /**
     * Saves current set of hotels to database
     * @throws IOException
     */
    public void saveHotels() throws IOException{
        DataWriter.saveHotels();
    }
}
