import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;

public class FlightFilter {
    private ArrayList<Flight> unfilteredFlights;
    private ArrayList<Flight> filteredFlights;
    private ArrayList<FlightTrait> filterParams;
    private Flight bestFlight;
    private RegisteredUser user;

    public FlightFilter(){
        unfilteredFlights = new ArrayList<Flight>();
        filteredFlights = new ArrayList<Flight>();
        
    }

    public ArrayList<Flight> searchForFlights(String departureCode, String arrivalCode) {
        ArrayList<Flight> allFlights = FlightList.getFlights();

        for (Flight flight : allFlights) {
            if (flight.getDepartureCode().equals(departureCode) && flight.getArrivalCode().equals(arrivalCode)) {
                unfilteredFlights.add(flight);
            }
        }
        return unfilteredFlights;
    }

    public ArrayList<Flight> filterFlights(FlightTrait flightTrait, ArrayList<Flight> flights){

        //sort by duration, low to high
        if (flightTrait.equals(FlightTrait.valueOf("DURATION"))) {
            boolean hasSwapped = true;
            while (hasSwapped) {
                hasSwapped = false;
                for (int i = 0; i < flights.size()-1; i++) {
                    if (Integer.valueOf(flights.get(i).getDuration()) > Integer.valueOf(flights.get(i+1).getDuration())) {
                        Flight temp = flights.get(i+1);
                        flights.set(i+1, flights.get(i));
                        flights.set(i, temp);
                        hasSwapped = true;
                    }
                }
            }
            return flights;
        }

        //sort by price, low to high
        if (flightTrait.equals(FlightTrait.valueOf("PRICE"))) {
            boolean hasSwapped = true;
            while (hasSwapped) {
                hasSwapped = false;
                for (int i = 0; i < flights.size()-1; i++) {
                    if (flights.get(i).getPrice() > flights.get(i+1).getPrice()) {
                        Flight temp = flights.get(i+1);
                        flights.set(i+1, flights.get(i));
                        flights.set(i, temp);
                        hasSwapped = true;
                    }
                }
            }
            return flights;
        }

        //sort by takeoff time, earliest to latest
        if (flightTrait.equals(FlightTrait.valueOf("TAKEOFF_TIME"))) {
            boolean hasSwapped = true;
            while (hasSwapped) {
                hasSwapped = false;
                for (int i = 0; i < flights.size()-1; i++) {
                    int minutesPastMidnightOfFirstIndex = (int)(Duration.between(LocalTime.MIDNIGHT, flights.get(i).getDepartureTime()).toMinutes());
                    int minutesPastMidnightOfSecondIndex = (int)(Duration.between(LocalTime.MIDNIGHT, flights.get(i+1).getDepartureTime()).toMinutes());

                    if (minutesPastMidnightOfFirstIndex > minutesPastMidnightOfSecondIndex) {
                        Flight temp = flights.get(i+1);
                        flights.set(i+1, flights.get(i));
                        flights.set(i, temp);
                        hasSwapped = true;
                    }
                }
            }
            return flights;
        }
        return null;
    }
    
}
