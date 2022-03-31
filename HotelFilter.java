import java.util.ArrayList;

public class HotelFilter {

    public ArrayList<Hotel> searchFoHotels(String airportCode){
        ArrayList<Hotel> hotels = HotelList.getHotelsByAirportCode(airportCode);

        return hotels;
    }
    
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

    public ArrayList<HotelRoom> filterHotelRooms(BedType bedType, ArrayList<HotelRoom> hotelRooms) {
        ArrayList<HotelRoom> filteredRooms = new ArrayList<HotelRoom>();

        for (HotelRoom room : hotelRooms) {
            if (room.getBedType().equals(bedType)) filteredRooms.add(room);
        }
        
        return filteredRooms;
    }
}
