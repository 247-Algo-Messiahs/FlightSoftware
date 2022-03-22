import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Nicolas Becker + Peyton Tucker
 * writes all data to JSON files
 */
public class DataWriter extends DataConstants{

    public static void saveUsers(ArrayList<User> users) {

    }
    
    public static void saveFlights(ArrayList<Flight> flights) {

    }

    public static void saveHotels(ArrayList<Hotel> hotels) {
        
    }

    //Get objects below

    public static JSONObject getUserJSON(User user){
        JSONObject userDetails = new JSONObject();

        return userDetails;
    }

    public static JSONObject getFlightJSON(Flight flight){
        JSONObject flightDetails = new JSONObject();

        flightDetails.put(FLIGHTS_FLIGHT_ID, flight.getFlightID().toString());
        flightDetails.put(FLIGHTS_DEPARTURE_CODE, flight.getDepartureCode());
        flightDetails.put(FLIGHTS_ARRIVAL_CODE, flight.getArrivalCode());
        flightDetails.put(FLIGHTS_DEPARTURE_TIME, flight.getDepartureTime());
        flightDetails.put(FLIGHTS_ARRIVAL_TIME, flight.getArrivalTime());
        flightDetails.put(FLIGHTS_DURATION, flight.getDuration());
        flightDetails.put(FLIGHTS_FIRST_SEATS, flight.getNumAvailFirstSeats());
        flightDetails.put(FLIGHTS_BUSINESS_SEATS, flight.getNumBusinessSeats());
        flightDetails.put(FLIGHTS_ECONOMY_SEATS, flight.getNumEconomySeats());
        flightDetails.put(FLIGHTS_IS_FULL, flight.getIsFull());
        flightDetails.put(FLIGHTS_IS_INTERNATIONAL, flight.getIsInternational());
        flightDetails.put(FLIGHTS_SEATS, flight.getSeats());

        return flightDetails;
    }

    public static JSONObject getHotelJSON(Hotel hotel){
        JSONObject hotelDetails = new JSONObject();

        hotelDetails.put(HOTELS_HOTEL_ID, hotel.getHotelID().toString());
        hotelDetails.put(HOTELS_HOTEL_RATING, hotel.getHotelRating());
        hotelDetails.put(HOTELS_CAPACITY, hotel.getCapacity());
        hotelDetails.put(HOTELS_NAME, hotel.getHotelName());
        hotelDetails.put(HOTELS_LOCATION, hotel.getLocation());
        hotelDetails.put(HOTELS_AIRPORT_CODE, hotel.getAirCode());
        
        /*
        protected static final String HOTELS_FILE_NAME = "hotels.json";
        protected static final String HOTELS_HOTEL_ID = "hotelID";
        protected static final String HOTELS_HOTEL_RATING = "hotelRating";
        protected static final String HOTELS_CAPACITY = "hotelCapacity";
        protected static final String HOTELS_NAME = "hotelName";
        protected static final String HOTELS_LOCATION = "location";
        protected static final String HOTELS_AIRPORT_CODE = "airCode";
        
        protected static final String ROOMS_FILE_NAME = "rooms.json";
        protected static final String ROOMS_ROOM_ID = "roomID";
        protected static final String ROOMS_HOTEL_ID = "hotelID";
        protected static final String ROOMS_NOT_AVAIL = "notAvail";
        protected static final String ROOMS_BED_TYPE = "bedType";
        protected static final String ROOMS_IS_SMOKING = "isSmoking";
        protected static final String ROOMS_HAS_BALCONY = "hasBalcony";
        protected static final String ROOMS_HAS_PULLOUT_COUCH = "hasPulloutCouch";
        */
        return hotelDetails;
    }
}
