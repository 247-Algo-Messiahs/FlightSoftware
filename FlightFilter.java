import java.util.ArrayList;

public class FlightFilter {
    private ArrayList<Flight> unfilteredFlights;
    private ArrayList<Flight> filteredFlights;
    private Preferences pref;
    private ArrayList<FlightTrait> filterParams;
    private Flight bestFlight;
    private RegisteredUser user;

    public FlightFilter(){

    }

    public Flight getBestFlight(){
        return null;
    }
    
}
