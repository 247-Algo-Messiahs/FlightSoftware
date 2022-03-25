import java.util.ArrayList;
import java.util.UUID;

public class FlightList {
    private static FlightList flightList;
    private static ArrayList<Flight> flights;

    private FlightList(){
        flights = DataLoader.loadFlights();
    }
    
    public static FlightList getInstance(){
        if (flightList == null) flightList = new FlightList();
        return flightList;
    }

    public static ArrayList<Flight> getFlights(){
        return flights;
    }

    public void setFlights(ArrayList<Flight> flightsArrayList) {
        flights = flightsArrayList;
    }

    public static Flight getFlightByUUID(UUID uuid) {
        for (int i = 0; i < flights.size(); i++) {
            Flight selectedFlight = flights.get(i);
            if (selectedFlight.getFlightID().equals(uuid)) return selectedFlight;
        }
        return null;
    }
}
