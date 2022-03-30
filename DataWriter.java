import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 * @author Nicolas Becker
 * writes all data to JSON files
 */
public class DataWriter extends DataConstants{

    public static void saveUsers() throws IOException {
        UserList users = UserList.getInstance();
        ArrayList<RegisteredUser> userList = users.getUsers();
        JSONArray jsonUsers = new JSONArray();

        //creating all the json objects
        for(int i=0; i<userList.size(); i++){
            jsonUsers.add(getUserJSON(userList.get(i)));
        }

        //write JSON file
        try(FileWriter file = new FileWriter("data/"+USERS_FILE_NAME)){
            file.write(jsonUsers.toJSONString());
            file.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void saveFlights() throws IOException{
        FlightList flights = FlightList.getInstance();
        ArrayList<Flight> flightList = flights.getFlights();
        JSONArray jsonFlights = new JSONArray();

        //creating all the json objects
        for(int i=0; i<flightList.size(); i++){
            jsonFlights.add(getFlightJSON(flightList.get(i)));
        }

        //write JSON file
        try(FileWriter file = new FileWriter("data/"+FLIGHTS_FILE_NAME)){
            file.write(jsonFlights.toJSONString());
            file.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void saveHotels() throws IOException{
        HotelList hotels = HotelList.getInstance();
        ArrayList<Hotel> hotelList = hotels.getHotels();
        JSONArray jsonHotels = new JSONArray();

        //creating all the json objects
        for(int i=0; i<hotelList.size(); i++){
            jsonHotels.add(getHotelJSON(hotelList.get(i)));
        }

        //write JSON file
        try(FileWriter file = new FileWriter("data/"+HOTELS_FILE_NAME)){
            file.write(jsonHotels.toJSONString());
            file.flush();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    //Get objects below and save them to JSONObjects to be referenced later

    public static JSONObject getUserJSON(RegisteredUser user){
        JSONObject userDetails = new JSONObject();
        JSONArray flightBookings = new JSONArray();
        ArrayList<FlightBooking> flightBooking = new ArrayList<>();
        JSONArray hotelBookings = new JSONArray();
        ArrayList<HotelBooking> hotelBooking = new ArrayList<>();
        flightBooking = user.getFlightBookings();
        hotelBooking = user.getHotelBookings();

        userDetails.put(USERS_USER_ID, user.getUserID().toString());
        userDetails.put(USERS_USERNAME, user.getUsername());
        userDetails.put(USERS_PASSWORD, user.getPassword());
        userDetails.put(USERS_FIRST_NAME, user.getFirstName());
        userDetails.put(USERS_LAST_NAME, user.getLastName());
        userDetails.put(USERS_AGE, user.getAge());
        userDetails.put(USERS_ADDRESS, user.getAddress());
        userDetails.put(USERS_EMAIL, user.getEmailAddress());
        userDetails.put(USERS_PHONE, user.getPhoneNumber());
        userDetails.put(USERS_FREQUENT_FLIER, user.getFrequentFlyer());
        userDetails.put(USERS_PASSPORT, user.getPassport());

        if(flightBooking!=null){
            for(int i=0; i<flightBooking.size(); i++){
                JSONObject fBooking = new JSONObject();
                JSONArray guests = new JSONArray();
    
                fBooking.put(FLIGHT_BOOKINGS_FLIGHT_ID, flightBooking.get(i).getFlightID().toString());
                fBooking.put(FLIGHT_BOOKINGS_SEAT_ID, flightBooking.get(i).getSeats());
                fBooking.put(FLIGHT_BOOKINGS_NUM_CHECKED_BAGS, flightBooking.get(i).getBags());
    
                for(int j=0; j<flightBooking.get(i).getGuests().size(); j++){
                    JSONObject guestInfo = new JSONObject();
                    guestInfo.put(GUESTS_FIRST_NAME, flightBooking.get(i).getGuests().get(j).getFirstName());
                    guestInfo.put(GUESTS_LAST_NAME, flightBooking.get(i).getGuests().get(j).getLastName());
                    guestInfo.put(GUESTS_AGE, flightBooking.get(i).getGuests().get(j).getAge());
                    guestInfo.put(GUESTS_SEAT_ID, flightBooking.get(i).getGuests().get(j).getSeatID());
                    guests.add(guestInfo);
                }
                fBooking.put(FLIGHT_BOOKINGS_GUEST_INFO, guests);
    
                flightBookings.add(fBooking);
            }
            userDetails.put(USERS_FLIGHT_BOOKING, flightBookings);
        } else{ userDetails.put(USERS_FLIGHT_BOOKING, ""); }        

        if(hotelBooking!=null){
            for(int i=0; i<hotelBooking.size(); i++){
                JSONObject hBooking = new JSONObject();
    
                hBooking.put(HOTEL_BOOKINGS_HOTEL_ID, hotelBooking.get(i).getHotelID().toString());
                hBooking.put(HOTEL_BOOKINGS_ROOM_ID, hotelBooking.get(i).getRoomID());
                //hBooking.put(HOTEL_BOOKINGS_BOOKED_DATES, value)
                JSONArray bookedArray = new JSONArray();
                for(int j=0; j<hotelBooking.get(i).getDates().size(); j++){
                    //bookedArray.add(hotelBooking.get(i).getDates().get(j).toString());

                    LocalDate date = hotelBooking.get(i).getDates().get(j);
                    bookedArray.add(date.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                }
                hBooking.put(HOTEL_BOOKINGS_BOOKED_DATES, bookedArray);
    
                hotelBookings.add(hBooking);
            }
            userDetails.put(USERS_HOTEL_BOOKING, hotelBookings);
        } else{ userDetails.put(USERS_HOTEL_BOOKING, ""); }
        
        return userDetails;
    }

    public static JSONObject getFlightJSON(Flight flight){
        JSONObject flightDetails = new JSONObject();
        JSONArray seatsArray = new JSONArray();
        JSONArray connections = new JSONArray();
        ArrayList<Seat> seats = new ArrayList<>();
        seats = flight.getSeats();

        flightDetails.put(FLIGHTS_FLIGHT_ID, flight.getFlightID().toString());
        flightDetails.put(FLIGHTS_DEPARTURE_CODE, flight.getDepartureCode());
        flightDetails.put(FLIGHTS_ARRIVAL_CODE, flight.getArrivalCode());
        flightDetails.put(FLIGHTS_DEPARTURE_TIME, flight.getDepartureTime().toString());
        flightDetails.put(FLIGHTS_ARRIVAL_TIME, flight.getArrivalTime().toString());
        flightDetails.put(FLIGHTS_DURATION, flight.getDuration());
        flightDetails.put(FLIGHTS_FIRST_SEATS, flight.getNumAvailFirstSeats());
        flightDetails.put(FLIGHTS_BUSINESS_SEATS, flight.getNumBusinessSeats());
        flightDetails.put(FLIGHTS_ECONOMY_SEATS, flight.getNumEconomySeats());
        flightDetails.put(FLIGHTS_IS_FULL, flight.getIsFull());
        flightDetails.put(FLIGHTS_IS_INTERNATIONAL, flight.getIsInternational());
        flightDetails.put(FLIGHTS_PRICE, flight.getPrice());

        for(int i=0; i<flight.getConnections().size(); i++){
            connections.add(flight.getConnectionsJSON().get(i).toString());
        }
        flightDetails.put(FLIGHTS_CONNECTIONS, connections);
        
        for(int i=0; i<seats.size(); i++){
            JSONObject seatJSON = new JSONObject();
            seatJSON.put(SEATS_SEAT_ID, seats.get(i).getSeatID());
            seatJSON.put(SEATS_SEAT_NUM, seats.get(i).getSeatNumber());
            seatJSON.put(SEATS_SEAT_CLASS, seats.get(i).getSeatClass().toString());
            seatJSON.put(SEATS_TYPE, seats.get(i).getSeatType().toString());
            seatJSON.put(SEATS_BOOKED, seats.get(i).getIsBooked());
            seatsArray.add(seatJSON);
        }
        
        flightDetails.put(FLIGHTS_SEATS, seatsArray);

        return flightDetails;
    }

    public static JSONObject getHotelJSON(Hotel hotel){
        JSONObject hotelDetails = new JSONObject();
        JSONArray roomArray = new JSONArray();
        ArrayList<HotelRoom> rooms = new ArrayList<>();
        rooms = hotel.getRooms();
        
        hotelDetails.put(HOTELS_HOTEL_ID, hotel.getHotelID().toString());
        hotelDetails.put(HOTELS_HOTEL_RATING, hotel.getHotelRating());
        hotelDetails.put(HOTELS_CAPACITY, hotel.getCapacity());
        hotelDetails.put(HOTELS_NAME, hotel.getHotelName());
        hotelDetails.put(HOTELS_LOCATION, hotel.getLocation());
        hotelDetails.put(HOTELS_AIRPORT_CODE, hotel.getAirportCode());
        
        for(int i=0; i<rooms.size(); i++){
            JSONObject roomJSON = new JSONObject();
            roomJSON.put(ROOMS_ROOM_ID, rooms.get(i).getRoomID());
            JSONArray availableArray = new JSONArray();
            for(int j=0; j<rooms.get(i).getNotAvail().size(); j++){
                LocalDate date = rooms.get(i).getNotAvail().get(j);
                availableArray.add(date.format(DateTimeFormatter.ofPattern("MM-dd-yyyy")));
            }
            roomJSON.put(ROOMS_NOT_AVAIL, availableArray);

            roomJSON.put(ROOMS_BED_TYPE, rooms.get(i).getBedType().toString());
            roomJSON.put(ROOMS_IS_SMOKING, rooms.get(i).isSmoking());
            roomJSON.put(ROOMS_HAS_BALCONY, rooms.get(i).hasBalcony());
            roomJSON.put(ROOMS_HAS_PULLOUT_COUCH, rooms.get(i).hasPulloutCouch());
            roomJSON.put(ROOMS_PRICE, rooms.get(i).getPrice());
            roomArray.add(roomJSON);
        }
        hotelDetails.put(HOTELS_ROOMS, roomArray);
        
        return hotelDetails;
    }
}
