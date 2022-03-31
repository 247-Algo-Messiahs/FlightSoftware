/**
 * @author Peyton Tucker
 * A class to store a list of Flight objects
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

public class FlightList {
    private static FlightList flightList;
    private static ArrayList<Flight> flights;

    /**
     * A private constructor to follow the Singleton design pattern
     */
    private FlightList(){
        flights = DataLoader.loadFlights();
        for (Flight flight : flights) {
            DataLoader.loadFlightConnections(flight);
        }
    }
    
    /**
     * A method to get the the instance of the FlightList singleton
     * @return the FlightList singleton
     */
    public static FlightList getInstance(){
        if (flightList == null) flightList = new FlightList();
        return flightList;
    }

    /**
     * Returns all flights currently stored in the flights variable
     * @return an ArrayList of all flights
     */
    public static ArrayList<Flight> getFlights(){
        return flights;
    }

    /**
     * Set this singleton's flight list to an ArrayList of flights
     * @param flightsArrayList the ArrayList of flights to set this singleton's flights field to
     */
    public void setFlights(ArrayList<Flight> flightsArrayList) {
        flights = flightsArrayList;
    }

    /**
     * Searches through the list of all flights to find one whose UUID matches
     * @param uuid the UUID of the desired flight object
     * @return the flight object whose UUID matches the one passed in
     */
    public static Flight getFlightByUUID(UUID uuid) {
        for (int i = 0; i < flights.size(); i++) {
            Flight selectedFlight = flights.get(i);
            if (selectedFlight.getFlightID().equals(uuid)) return selectedFlight;
        }
        return null;
    }

    /**
     * Calls the DataWriter to save all flight objects currently being stored into flights.json
     * @throws IOException if an error occurs while DataWriter is attempting to save
     */
    public void saveFlights() throws IOException{
        DataWriter.saveFlights();
    }
}
