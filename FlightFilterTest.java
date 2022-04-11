import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;





public class FlightFilterTest {
    public DataLoader dataLoader = DataLoader.getInstance();
    public Facade facade = Facade.getInstance();
    public FlightList flightList = FlightList.getInstance();
    public ArrayList<Flight> allFlights = FlightList.getFlights();

 

    private ArrayList<Flight> preservedFlights = new ArrayList<Flight>();



@BeforeEach
public void setup(){
    DataLoader.loadFlights();
    preservedFlights = FlightList.getFlights();

    ArrayList<Flight> blankFlights = new ArrayList<Flight>();
    flightList.setFlights(blankFlights);
}

@AfterEach
public void tearDown(){
    flightList.setFlights(preservedFlights);

}
@Test 
void filterDuration(){
    FlightFilter f = new FlightFilter();

    UUID flightID = UUID.randomUUID();

    ArrayList<Seat> oneSeat = new ArrayList<Seat>();
        int seatID = 3;
        Seat seat = new Seat(seatID, "AISLE", "BUSINESS", "1A", flightID, false);
        oneSeat.add(seat);

    ArrayList<Flight> randomFlightFilter = new ArrayList<Flight>();
        Flight flight1 = new Flight(flightID, "ATL", "HND", LocalTime.parse("07:00"), LocalTime.parse("09:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomFlightFilter.add(flight1);
        Flight flight2 = new Flight(flightID, "ATL", "HND", LocalTime.parse("07:00"), LocalTime.parse("10:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomFlightFilter.add(flight2);
        Flight flight3 = new Flight(flightID, "ATL", "HND", LocalTime.parse("07:00"), LocalTime.parse("11:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomFlightFilter.add(flight3);
        Flight flight4 = new Flight(flightID, "ATL", "HND", LocalTime.parse("07:00"), LocalTime.parse("12:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomFlightFilter.add(flight4);
       
        flightList.setFlights(randomFlightFilter);

    ArrayList<Flight> randomFlightFilterTest = new ArrayList<Flight>();
        randomFlightFilterTest.add(flight4);
        randomFlightFilterTest.add(flight2);
        randomFlightFilterTest.add(flight3);
        randomFlightFilterTest.add(flight1);

        assertEquals(randomFlightFilter,f.filterFlights(FlightTrait.DURATION, randomFlightFilterTest));
}
}
