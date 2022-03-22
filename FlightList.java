import java.util.ArrayList;
import java.util.UUID;

public class FlightList {
    private static FlightList flightList;
    private ArrayList<Flight> flights;

    private FlightList(){
        //call dataloader.getallfights
    }
    
    public static FlightList getInstance(){
        if (flightList == null) flightList = new FlightList();
        return flightList;
    }

    public ArrayList<Flight> getFlights(){
        return this.flights;
    }

    public void setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public Flight getFlightByUUID(UUID uuid) {
        for (int i = 0; i < this.flights.size(); i++) {
            Flight selectedFlight = this.flights.get(i);
            if (selectedFlight.getFlightID().equals(uuid)) return selectedFlight;
        }
        return null;
    }
}
