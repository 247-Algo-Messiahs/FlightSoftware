import java.util.ArrayList;

public class HotelFilter {
    private ArrayList<HotelRoom> unfilteredHotels;
    private ArrayList<HotelRoom> filteredHotels;
    private ArrayList<BedType> filterParams;

    public HotelFilter(){
        
    }
    
    public ArrayList<HotelRoom> searchForHotels(String airportCode) {
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
}
