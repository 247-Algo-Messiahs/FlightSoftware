import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import java.time.*;
import java.time.format.DateTimeFormatter;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class DataLoader extends DataConstants{
    private static DataLoader dataLoader;

    private DataLoader() {
        
    }

    public static DataLoader getInstance() {
        if (dataLoader == null) dataLoader = new DataLoader();
        return dataLoader;
    }
    
    public static ArrayList<RegisteredUser> loadUsers() {
        ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();

        try {
            FileReader reader = new FileReader("data/" + USERS_FILE_NAME);
            JSONParser parser = new JSONParser();
			JSONArray allUsersJSON = (JSONArray)parser.parse(reader);

            for (int i = 0; i < allUsersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)allUsersJSON.get(i);

                UUID userID = UUID.fromString((String)userJSON.get(USERS_USER_ID));
                String username = (String)userJSON.get(USERS_USERNAME);
                String password = (String)userJSON.get(USERS_PASSWORD);
                String firstName = (String)userJSON.get(USERS_FIRST_NAME);
                String lastName = (String)userJSON.get(USERS_LAST_NAME);
                long age = (long)userJSON.get(USERS_AGE);
                String address = (String)userJSON.get(USERS_ADDRESS);
                String email = (String)userJSON.get(USERS_EMAIL);
                String phone = (String)userJSON.get(USERS_PHONE);
                boolean frequentFlier = (boolean)userJSON.get(USERS_FREQUENT_FLIER);
                boolean passport = (boolean)userJSON.get(USERS_PASSPORT);
                JSONArray flightBooking = (JSONArray)userJSON.get(USERS_FLIGHT_BOOKING);
                JSONArray hotelBooking = (JSONArray) userJSON.get(USERS_HOTEL_BOOKING);

                users.add(new RegisteredUser(userID, firstName, lastName, address, phone, username, password, passport, (int)age, email, frequentFlier, flightBooking, hotelBooking));
            }            
            
            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void loadUserFlightBookings(RegisteredUser user) {
        ArrayList<FlightBooking> bookings = new ArrayList<FlightBooking>();
        
        JSONArray flightBookingArray = user.getFlightBookingJSON();

        for (int i = 0; i < flightBookingArray.size(); i++) {
            JSONObject flightBookingJSON = (JSONObject)flightBookingArray.get(i);

            JSONArray guestInfoArray = (JSONArray)flightBookingJSON.get(FLIGHT_BOOKINGS_GUEST_INFO);
            ArrayList<Guest> guests = new ArrayList<Guest>();

            for (int k = 0; k < guestInfoArray.size(); k++) {
                JSONObject guestJSON = (JSONObject) guestInfoArray.get(k);

                String firstName = (String)guestJSON.get(GUESTS_FIRST_NAME);
                String lastName = (String)guestJSON.get(GUESTS_LAST_NAME);
                long age = (long)guestJSON.get(GUESTS_AGE);
                long seatID = (long)guestJSON.get(GUESTS_SEAT_ID);

                Guest guest = new Guest((int)age, firstName, lastName, (int)seatID);
                guests.add(guest);
            }

            UUID flightID = UUID.fromString((String)flightBookingJSON.get(FLIGHT_BOOKINGS_FLIGHT_ID));
            long seatID = (long)flightBookingJSON.get(FLIGHT_BOOKINGS_SEAT_ID);
            long numCheckedBags = (long)flightBookingJSON.get(FLIGHT_BOOKINGS_NUM_CHECKED_BAGS);

            loadFlightConnections(FlightList.getFlightByUUID(flightID));

            FlightBooking booking = new FlightBooking(guests, flightID, (int)seatID, (int)numCheckedBags);
            bookings.add(booking);
        }

        user.setFlightBookings(bookings);
    }



    public static ArrayList<Flight> loadFlights() {
        ArrayList<Flight> flights = new ArrayList<Flight>();

        try {
            FileReader reader = new FileReader("data/" + FLIGHTS_FILE_NAME);
            JSONParser parser = new JSONParser();
			JSONArray allFlightsJSON = (JSONArray)parser.parse(reader);

            for (int i = 0; i < allFlightsJSON.size(); i++) {
                JSONObject FlightJSON = (JSONObject)allFlightsJSON.get(i);

                UUID flightID = UUID.fromString((String)FlightJSON.get(FLIGHTS_FLIGHT_ID));
                String departureCode = (String)FlightJSON.get(FLIGHTS_DEPARTURE_CODE);
                String arrivalCode = (String)FlightJSON.get(FLIGHTS_ARRIVAL_CODE);
                LocalTime departureTime = LocalTime.parse((String)FlightJSON.get(FLIGHTS_DEPARTURE_TIME));
                LocalTime arrivalTime = LocalTime.parse((String)FlightJSON.get(FLIGHTS_ARRIVAL_TIME));
                long numAvailFirstSeats = (long)FlightJSON.get(FLIGHTS_FIRST_SEATS);
                long numAvailBusinessSeats = (long)FlightJSON.get(FLIGHTS_BUSINESS_SEATS);
                long numAvailEconomySeats = (long)FlightJSON.get(FLIGHTS_ECONOMY_SEATS);
                boolean isFull = (boolean)FlightJSON.get(FLIGHTS_IS_FULL);
                boolean isInternational = (boolean) FlightJSON.get(FLIGHTS_IS_INTERNATIONAL);
                long price = (long) FlightJSON.get(FLIGHTS_PRICE);
                JSONArray connectionsJSON = (JSONArray)FlightJSON.get(FLIGHTS_CONNECTIONS);

                ArrayList<Seat> seats = new ArrayList<Seat>();
                JSONArray allSeatsJSON = (JSONArray)FlightJSON.get(FLIGHTS_SEATS);
                //add embedded seats objects
                for (int k = 0; k < 12; k++) {
                    JSONObject seatJSON = (JSONObject)allSeatsJSON.get(k);
                    long seatID = (long)seatJSON.get(SEATS_SEAT_ID);
                    String seatNum = (String)seatJSON.get(SEATS_SEAT_NUM);
                    String seatClass = (String)seatJSON.get(SEATS_SEAT_CLASS);
                    String seatType = (String)seatJSON.get(SEATS_TYPE);
                    boolean booked = (boolean)seatJSON.get(SEATS_BOOKED);

                    seats.add(new Seat((int)seatID, seatType, seatClass, seatNum, flightID, booked));
                }

                flights.add(new Flight(flightID, departureCode, arrivalCode, departureTime, arrivalTime, 
                (int)numAvailFirstSeats, (int)numAvailBusinessSeats, (int)numAvailEconomySeats, isFull, isInternational, seats, (int)price, connectionsJSON));
            }            
            
            return flights;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void loadFlightConnections(Flight flight) {
        ArrayList<Flight> connections = new ArrayList<Flight>();

        JSONArray connectionsArray = flight.getConnectionsJSON();

        for (int i = 0; i < connectionsArray.size(); i++) {
            UUID flightUUID = UUID.fromString((String)connectionsArray.get(i));
            connections.add(FlightList.getFlightByUUID(flightUUID));
        }

        flight.setConnections(connections);
    }

    public static void loadUserHotelBookings(RegisteredUser user) {
        ArrayList<HotelBooking> bookings = new ArrayList<HotelBooking>();
        
        JSONArray hotelBookingArray = user.getHotelBookingJSON();

        for (int i = 0; i < hotelBookingArray.size(); i++) {
            JSONObject hotelBookingJSON = (JSONObject)hotelBookingArray.get(i);

            UUID hotelID = UUID.fromString((String)hotelBookingJSON.get(HOTEL_BOOKINGS_HOTEL_ID));
            long roomID = (long)hotelBookingJSON.get(HOTEL_BOOKINGS_ROOM_ID);

            JSONArray bookedDatesArray = (JSONArray)hotelBookingJSON.get(HOTEL_BOOKINGS_BOOKED_DATES);
            ArrayList<LocalDate> bookedDates = new ArrayList<LocalDate>();

            for (int k = 0; k < bookedDatesArray.size(); k++) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
                
                LocalDate date = LocalDate.parse((String)bookedDatesArray.get(k), formatter);
                bookedDates.add(date);
            }
            
            HotelBooking booking = new HotelBooking(hotelID, (int)roomID, bookedDates);
            bookings.add(booking);
        }

        user.setHotelBookings(bookings);
    }

    public static ArrayList<Hotel> loadHotels() {
        ArrayList<Hotel> hotels = new ArrayList<Hotel>();

        try {
            FileReader reader = new FileReader("data/" + HOTELS_FILE_NAME);
            JSONParser parser = new JSONParser();
			JSONArray allHotelsJSON = (JSONArray)parser.parse(reader);

            for (int i = 0; i < allHotelsJSON.size(); i++) {
                JSONObject hotelJSON = (JSONObject)allHotelsJSON.get(i);

                UUID hotelID = UUID.fromString((String)hotelJSON.get(HOTELS_HOTEL_ID));
                long hotelRating = (long)hotelJSON.get(HOTELS_HOTEL_RATING);
                long capacity = (long)hotelJSON.get(HOTELS_CAPACITY);
                String hotelName = (String)hotelJSON.get(HOTELS_NAME);
                String location = (String)hotelJSON.get(HOTELS_LOCATION);
                String airportCode = (String)hotelJSON.get(HOTELS_AIRPORT_CODE);

                ArrayList<HotelRoom> rooms = new ArrayList<HotelRoom>();
                JSONArray allRoomsJSON = (JSONArray)hotelJSON.get(HOTELS_ROOMS);
                //add embedded rooms objects
                for (int k = 0; k < allRoomsJSON.size(); k++) {
                    JSONObject roomJSON = (JSONObject)allRoomsJSON.get(k);

                    long roomID = (long)roomJSON.get(ROOMS_ROOM_ID);
                    long price = (long)roomJSON.get(ROOMS_PRICE);
                    BedType bedType = BedType.valueOf((String)roomJSON.get(ROOMS_BED_TYPE));
                    boolean isSmoking = (boolean)roomJSON.get(ROOMS_IS_SMOKING);
                    boolean hasBalcony = (boolean)roomJSON.get(ROOMS_HAS_BALCONY);
                    boolean hasPulloutCouch = (boolean)roomJSON.get(ROOMS_HAS_PULLOUT_COUCH);

                    ArrayList<LocalDate> notAvailDatesArray = new ArrayList<LocalDate>();
                    JSONArray notAvailDatesJSON = (JSONArray)roomJSON.get(ROOMS_NOT_AVAIL);

                    for (int j = 0; j < notAvailDatesJSON.size(); j++) {
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-uuuu");
                
                        LocalDate date = LocalDate.parse((String)notAvailDatesJSON.get(j), formatter);
                        notAvailDatesArray.add(date); 
                    }

                    rooms.add(new HotelRoom((int)roomID, (int) price, notAvailDatesArray, bedType, isSmoking, hasBalcony, hasPulloutCouch));
                }

                hotels.add(new Hotel(hotelID, (int)hotelRating, (int)capacity, hotelName, location, airportCode, rooms));
            }            
            
            return hotels;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
