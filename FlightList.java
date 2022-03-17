import java.util.ArrayList;

public class FlightList {
    private static FlightList flightList;
    private ArrayList<Flight> flights;

    private FlightList(){

    }

    public FlightList getInstance(){
        if (this.flightList == null) this.flightList = new FlightList();
        return this.flightList;
    }

    public Flight getFlights(String keyword){
        return null;
    }
}
