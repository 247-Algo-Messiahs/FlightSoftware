import java.util.ArrayList;

public class FlightList {
    private static FlightList flightList;
    private ArrayList<Flight> flights;

    private FlightList(){

    }

    public static FlightList getInstance(){
        if (flightList == null) flightList = new FlightList();
        return flightList;
    }

    public Flight getFlights(String keyword){
        return null;
    }
}
