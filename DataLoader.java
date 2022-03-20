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
                String seatType = (String)userJSON.get(USERS_SEAT_TYPE);
                String seatClass = (String)userJSON.get(USERS_SEAT_CLASS);
                String hotelRoom = (String)userJSON.get(USERS_HOTEL_ROOM);
                String airportCode = (String)userJSON.get(USERS_AIRPORT_CODE);

                Preferences preferences = new Preferences(seatType, seatClass, hotelRoom, airportCode);

                users.add(new RegisteredUser(userID, firstName, lastName, address, phone, username, password, passport, (int)age, email, frequentFlier, preferences));
            }            
            
            return users;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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
                LocalDate departureDate = null;
                LocalTime arrivalTime = LocalTime.parse((String)FlightJSON.get(FLIGHTS_ARRIVAL_TIME));
                LocalDate arrivalDate = null;
                long numAvailFirstSeats = (long)FlightJSON.get(FLIGHTS_FIRST_SEATS);
                long numAvailBusinessSeats = (long)FlightJSON.get(FLIGHTS_BUSINESS_SEATS);
                long numAvailEconomySeats = (long)FlightJSON.get(FLIGHTS_ECONOMY_SEATS);
                boolean isFull = (boolean)FlightJSON.get(FLIGHTS_IS_FULL);
                boolean isInternational = (boolean) FlightJSON.get(FLIGHTS_IS_INTERNATIONAL);

                flights.add(new Flight(flightID, departureCode, arrivalCode, departureTime, arrivalTime, 
                (int)numAvailFirstSeats, (int)numAvailBusinessSeats, (int)numAvailEconomySeats, isFull, isInternational));
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
