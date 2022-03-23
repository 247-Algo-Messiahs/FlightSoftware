import java.io.*;
import java.util.ArrayList;
import java.util.UUID;
import java.time.*;

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
    
    public ArrayList<RegisteredUser> loadUsers() {
        ArrayList<RegisteredUser> users = new ArrayList<RegisteredUser>();

        try {
            FileReader reader = new FileReader("data/" + USERS_FILE_NAME);
            JSONParser parser = new JSONParser();
			JSONArray allUsersJSON = (JSONArray)parser.parse(reader);

            for (int i = 0; i < allUsersJSON.size(); i++) {
                JSONObject userJSON = (JSONObject)allUsersJSON.get(i);

                String userID = (String)userJSON.get(USERS_USER_ID);
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

    public ArrayList<FlightBooking> loadUserFlightBookings(RegisteredUser user) {
        ArrayList<FlightBooking> bookings = new ArrayList<FlightBooking>();
        
        JSONArray flightBookingArray = user.getFlightBooking();

        for (int i = 0; i < flightBookingArray.size(); i++) {
            JSONObject flightBookingJSON = (JSONObject)flightBookingArray.get(i);

            JSONArray guestInfoArray = (JSONArray)flightBookingJSON.get(FLIGHT_BOOKINGS_GUEST_INFO);
            ArrayList<Guest> guests = new ArrayList<Guest>();

            for (int k = 0; k < guestInfoArray.size(); k++) {
                JSONObject guestJSON = (JSONObject) guestInfoArray.get(k);

                String firstName = (String)guestJSON.get(GUESTS_FIRST_NAME);
                String lastName = (String)guestJSON.get(GUESTS_LAST_NAME);
                long age = (long)guestJSON.get(GUESTS_AGE);

                Guest guest = new Guest((int)age, firstName, lastName);
                guests.add(guest);
            }

            UUID flightID = UUID.fromString((String)flightBookingJSON.get(FLIGHT_BOOKINGS_FLIGHT_ID));
            long seatID = (long)flightBookingJSON.get(FLIGHT_BOOKINGS_SEAT_ID);
            long numCheckedBags = (long)flightBookingJSON.get(FLIGHT_BOOKINGS_NUM_CHECKED_BAGS);

            FlightBooking booking = new FlightBooking(guests, flightID, (int)seatID, (int)numCheckedBags);
            bookings.add(booking);
        }

        return bookings;
    }

    public ArrayList<Flight> loadFlights() {
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
                (int)numAvailFirstSeats, (int)numAvailBusinessSeats, (int)numAvailEconomySeats, isFull, isInternational, seats));
            }            
            
            return flights;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public ArrayList<Hotel> getAllHotels() {
        return null;
    }

}
