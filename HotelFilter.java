import java.util.ArrayList;

/**
 * @author Nicolas Becker, Peyton Tucker, Avery Slomnicki, Austin Hanson
 */
public class HotelFilter {

    /**
     * 
     * @param airportCode
     * @return ArrayList of Hotel objects matching a given airportCode
     */
    public ArrayList<Hotel> searchFoHotels(String airportCode){
        ArrayList<Hotel> hotels = HotelList.getHotelsByAirportCode(airportCode);

        return hotels;
    }
    
    /**
     * 
     * @param airportCode
     * @return ArrayList of HotelRoom objects defined by airportCode
     */
    public ArrayList<HotelRoom> searchForHotelRooms(String airportCode) {
        ArrayList<Hotel> hotels = HotelList.getHotelsByAirportCode(airportCode);
        ArrayList<HotelRoom> hotelRooms = new ArrayList<HotelRoom>();

        for (Hotel hotel : hotels) {
            ArrayList<HotelRoom> rooms = hotel.getRooms();
            for (HotelRoom room : rooms) {
                hotelRooms.add(room);
            }
        }

        return hotelRooms;
    }

    /**
     * 
     * @param bedType
     * @param hotelRooms
     * @return ArrayList of HotelRoom defined by bedType and a given set of HotelRooms
     */
    public ArrayList<HotelRoom> filterHotelRooms(BedType bedType, ArrayList<HotelRoom> hotelRooms) {
        ArrayList<HotelRoom> filteredRooms = new ArrayList<HotelRoom>();

        for (HotelRoom room : hotelRooms) {
            if (room.getBedType().equals(bedType)) filteredRooms.add(room);
        }
        
        return filteredRooms;
    }
}
