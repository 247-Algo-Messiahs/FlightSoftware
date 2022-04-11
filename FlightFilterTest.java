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
void testDurationFilter(){
    FlightFilter f = new FlightFilter();

    UUID flightID = UUID.randomUUID();

    ArrayList<Seat> oneSeat = new ArrayList<Seat>();
        int seatID = 3;
        Seat seat = new Seat(seatID, "AISLE", "BUSINESS", "1A", flightID, false);
        oneSeat.add(seat);

    ArrayList<Flight> randomDurationFilter = new ArrayList<Flight>();
        Flight flight1 = new Flight(flightID, "DFW", "JFK", LocalTime.parse("07:00"), LocalTime.parse("09:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomDurationFilter.add(flight1);
        Flight flight2 = new Flight(flightID, "DFW", "JFK", LocalTime.parse("07:00"), LocalTime.parse("10:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomDurationFilter.add(flight2);
        Flight flight3 = new Flight(flightID, "DFW", "JFK", LocalTime.parse("07:00"), LocalTime.parse("11:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomDurationFilter.add(flight3);
        Flight flight4 = new Flight(flightID, "DFW", "JFK", LocalTime.parse("07:00"), LocalTime.parse("12:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomDurationFilter.add(flight4);
       
        flightList.setFlights(randomDurationFilter);

    ArrayList<Flight> randomDurationFilterTest = new ArrayList<Flight>();
    randomDurationFilterTest.add(flight4);
    randomDurationFilterTest.add(flight2);
    randomDurationFilterTest.add(flight3);
    randomDurationFilterTest.add(flight1);

        assertEquals(randomDurationFilter,f.filterFlights(FlightTrait.DURATION, randomDurationFilterTest));
    }
@Test
void testTakeoffFilter(){
    FlightFilter f = new FlightFilter();

    UUID flightID = UUID.randomUUID();

    ArrayList<Seat> oneSeat = new ArrayList<Seat>();
        int seatID = 3;
        Seat seat = new Seat(seatID, "AISLE", "BUSINESS", "1A", flightID, false);
        oneSeat.add(seat);

    ArrayList<Flight> randomTakeoffFilter = new ArrayList<Flight>();
        Flight flight1 = new Flight(flightID, "DFW", "JFK", LocalTime.parse("04:00"), LocalTime.parse("05:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomTakeoffFilter.add(flight1);
        Flight flight2 = new Flight(flightID, "DFW", "JFK", LocalTime.parse("06:00"), LocalTime.parse("07:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomTakeoffFilter.add(flight2);
        Flight flight3 = new Flight(flightID, "DFW", "JFK", LocalTime.parse("08:00"), LocalTime.parse("09:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomTakeoffFilter.add(flight3);
        Flight flight4 = new Flight(flightID, "DFW", "JFK", LocalTime.parse("10:00"), LocalTime.parse("12:00"), 3, 3, 3, false, false, oneSeat, 100, null);
        randomTakeoffFilter.add(flight4);

        flightList.setFlights(randomTakeoffFilter);

        ArrayList<Flight> randomTakeoffFilterTest = new ArrayList<Flight>();
        randomTakeoffFilterTest.add(flight3);
        randomTakeoffFilterTest.add(flight1);
        randomTakeoffFilterTest.add(flight4);
        randomTakeoffFilterTest.add(flight2);

        assertEquals(randomTakeoffFilter,f.filterFlights(FlightTrait.TAKEOFF_TIME, randomTakeoffFilterTest));

}
}
