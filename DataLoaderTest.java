import org.junit.jupiter.api.Test;
import org.json.simple.JSONArray;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

public class DataLoaderTest {

    private FlightList flightList = FlightList.getInstance();
    private HotelList hotelList = HotelList.getInstance();
    private UserList userList = UserList.getInstance();

    private ArrayList<RegisteredUser> preservedUsers = new ArrayList<RegisteredUser>();
    private ArrayList<Flight> preservedFlights = new ArrayList<Flight>();
    private ArrayList<Hotel> preservedHotels = new ArrayList<Hotel>();
    
    @BeforeEach
    public void setup() {
        DataLoader.loadFlights();
        preservedFlights = FlightList.getFlights();
        
        DataLoader.loadHotels();
        preservedHotels = HotelList.getHotels();

        DataLoader.loadUsers();
        preservedUsers = userList.getUsers();
        
        ArrayList<RegisteredUser> blankUsers = new ArrayList<RegisteredUser>();
        userList.setUsers(blankUsers);
        
        ArrayList<Hotel> blankHotels = new ArrayList<Hotel>();
        hotelList.setHotels(blankHotels);

        ArrayList<Flight> blankFlights = new ArrayList<Flight>();
        flightList.setFlights(blankFlights);
    }

    @AfterEach
    public void tearDown() throws IOException {
        flightList.setFlights(preservedFlights);
        hotelList.setHotels(preservedHotels);
        userList.setUsers(preservedUsers);

        DataWriter.saveFlights();
        DataWriter.saveHotels();
        DataWriter.saveUsers();
    }

    @Test
    public void testLoadUsers() throws IOException {
        ArrayList<RegisteredUser> oneUserList = new ArrayList<RegisteredUser>();
        
        UUID userUUID = UUID.randomUUID();
        RegisteredUser user = new RegisteredUser(userUUID, "Billy", "Green", "120 Stockhead Drive", "555-123-1234", "billygreen", "billyg123",  true, 31, "billyg@gmail.com");
        oneUserList.add(user);

        userList.setUsers(oneUserList);
        
        DataWriter.saveUsers();
        DataLoader.loadUsers();

        assertEquals("billygreen", UserList.getRegisteredUserByUUID(userUUID).getUsername());
    }

    @Test
    public void testForTwoUsersSize() throws IOException {
        ArrayList<RegisteredUser> twoUserList = new ArrayList<RegisteredUser>();
        
        UUID user1UUID = UUID.randomUUID();
        UUID user2UUID = UUID.randomUUID();
        RegisteredUser user1 = new RegisteredUser(user1UUID, "Billy", "Green", "120 Stockhead Drive", "555-123-1234", "billygreen", "billyg123",  true, 31, "billyg@gmail.com");
        RegisteredUser user2 = new RegisteredUser(user2UUID, "Molly", "Brown", "120 Stockhead Drive", "555-123-1234", "mollybrown", "mollyb123",  true, 34, "mollyb@gmail.com");
        twoUserList.add(user1);
        twoUserList.add(user2);

        userList.setUsers(twoUserList);
        
        DataWriter.saveUsers();
        DataLoader.loadUsers();

        assertEquals(2, userList.getUsers().size());
    }

    @Test
    public void testLoadZeroUsersSize() throws IOException {
        ArrayList<RegisteredUser> emptyUserList = new ArrayList<RegisteredUser>();

        userList.setUsers(emptyUserList);
        
        DataWriter.saveUsers();
        DataLoader.loadUsers();

        assertEquals(0, userList.getUsers().size());
    }

    @Test
    public void testLoadUserFlightBookings() throws IOException {
        ArrayList<RegisteredUser> oneUserList = new ArrayList<RegisteredUser>();
        
        UUID userUUID = UUID.randomUUID();
        RegisteredUser user = new RegisteredUser(userUUID, "Billy", "Green", "120 Stockhead Drive", "555-123-1234", "billygreen", "billyg123",  true, 31, "billyg@gmail.com");
        oneUserList.add(user);

        userList.setUsers(oneUserList);

        UUID flightID = UUID.randomUUID();
        LocalTime departureTime = LocalTime.parse("07:00");
        LocalTime arrivalTime = LocalTime.parse("09:00");

        ArrayList<Seat> oneSeat = new ArrayList<Seat>();
        int seatID = 3;
        Seat seat = new Seat(seatID, "AISLE", "BUSINESS", "1A", flightID, false);
        oneSeat.add(seat);

        ArrayList<Flight> oneFlight = new ArrayList<Flight>();
        Flight flight = new Flight(flightID, "ATL", "HND", departureTime, arrivalTime, 3, 3, 3, false, false, oneSeat, 100, null);
        oneFlight.add(flight);

        flightList.setFlights(oneFlight);

        ArrayList<Guest> emptyGuests = new ArrayList<Guest>();

        FlightBooking flightBooking = new FlightBooking(emptyGuests, flightID, seatID, 1);

        ArrayList<FlightBooking> bookingsList = new ArrayList<FlightBooking>();
        bookingsList.add(flightBooking);
        user.setFlightBookings(bookingsList);

        DataWriter.saveUsers();
        DataLoader.loadUsers();

        assertEquals(flightID, UserList.getRegisteredUserByUUID(userUUID).getFlightBookings().get(0).getFlightID());
    }

    @Test
    public void testLoadFlights() throws IOException {
        UUID flightID = UUID.randomUUID();

        LocalTime departureTime = LocalTime.parse("07:00");
        LocalTime arrivalTime = LocalTime.parse("09:00");

        ArrayList<Seat> oneSeat = new ArrayList<Seat>();
        int seatID = 3;
        Seat seat = new Seat(seatID, "AISLE", "BUSINESS", "1A", flightID, false);
        oneSeat.add(seat);

        ArrayList<Flight> oneFlight = new ArrayList<Flight>();
        Flight flight = new Flight(flightID, "ATL", "HND", departureTime, arrivalTime, 3, 3, 3, false, false, oneSeat, 100, null);
        oneFlight.add(flight);

        flightList.setFlights(oneFlight);

        DataWriter.saveFlights();
        DataLoader.loadFlights();

        assertEquals(flightID, FlightList.getFlights().get(0).getFlightID());
    }

    @Test
    public void testLoadZeroFlightsSize() throws IOException {
        ArrayList<Flight> emptyFlights = new ArrayList<Flight>();

        flightList.setFlights(emptyFlights);

        DataWriter.saveFlights();
        DataLoader.loadFlights();

        assertEquals(0, FlightList.getFlights().size());
    }

    //Fails because of an inconsistency in how flight connections are written to JSON from each flight object; results in null pointer exception. Requires changing DataWriter to resolve
    @Test
    public void testLoadFlightConnections() throws IOException {
        UUID flightID = UUID.randomUUID();
        UUID connectionFlightID = UUID.randomUUID();

        LocalTime departureTime = LocalTime.parse("07:00");
        LocalTime arrivalTime = LocalTime.parse("09:00");

        ArrayList<Seat> oneSeat = new ArrayList<Seat>();
        int seatID = 3;
        Seat seat = new Seat(seatID, "AISLE", "BUSINESS", "1A", flightID, false);
        oneSeat.add(seat);

        ArrayList<Flight> oneFlight = new ArrayList<Flight>();
        Flight flight = new Flight(flightID, "ATL", "HND", departureTime, arrivalTime, 3, 3, 3, false, false, oneSeat, 100, null);

        Flight connection = new Flight(connectionFlightID, "ATL", "HND", departureTime, arrivalTime, 3, 3, 3, false, false, oneSeat, 100, null);
        ArrayList<Flight> flightConnections = new ArrayList<Flight>();
        flightConnections.add(connection);
        flight.setConnections(flightConnections);
        oneFlight.add(flight);

        flightList.setFlights(oneFlight);

        DataWriter.saveFlights();
        DataLoader.loadFlights();

        assertEquals(connectionFlightID, FlightList.getFlightByUUID(flightID).getConnections().get(0).getFlightID());
    }

    @Test
    public void loadUserHotelBookings() throws IOException {
        ArrayList<RegisteredUser> oneUserList = new ArrayList<RegisteredUser>();
        
        UUID userUUID = UUID.randomUUID();
        RegisteredUser user = new RegisteredUser(userUUID, "Billy", "Green", "120 Stockhead Drive", "555-123-1234", "billygreen", "billyg123",  true, 31, "billyg@gmail.com");
        oneUserList.add(user);

        userList.setUsers(oneUserList);
        
        UUID hotelID = UUID.randomUUID();
        int roomID = 1;

        ArrayList<LocalDate> notAvail = new ArrayList<LocalDate>();
        LocalDate date1 = LocalDate.parse("2023-01-01");
        LocalDate date2 = LocalDate.parse("2023-01-04");
        notAvail.add(date1);
        notAvail.add(date2);

        ArrayList<HotelRoom> oneRoomArray = new ArrayList<HotelRoom>();
        HotelRoom room = new HotelRoom(roomID, 100, notAvail, BedType.DOUBLE, false, true, true);
        oneRoomArray.add(room);
        
        Hotel hotel = new Hotel(hotelID, 5, 12, "Tristar Deluxe", "342 Exchange Hwy", "ATL", oneRoomArray);

        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        LocalDate checkInDate = LocalDate.parse("2023-01-01");
        LocalDate checkOutDate = LocalDate.parse("2023-01-02");
        dates.add(checkInDate);
        dates.add(checkOutDate);
        
        ArrayList<HotelBooking> bookingsArray = new ArrayList<HotelBooking>();
        HotelBooking hotelBooking = new HotelBooking(hotelID, roomID, dates);
        bookingsArray.add(hotelBooking);

        user.setHotelBookings(bookingsArray);

        DataWriter.saveUsers();
        DataLoader.loadUsers();

        assertEquals(hotelID, UserList.getRegisteredUserByUUID(userUUID).getHotelBookings().get(0).getHotelID());
    }

    @Test
    public void testLoadHotels() throws IOException {
        UUID hotelID = UUID.randomUUID();
        int roomID = 1;

        ArrayList<LocalDate> notAvail = new ArrayList<LocalDate>();
        LocalDate date1 = LocalDate.parse("2023-01-01");
        LocalDate date2 = LocalDate.parse("2023-01-04");
        notAvail.add(date1);
        notAvail.add(date2);

        ArrayList<HotelRoom> oneRoomArray = new ArrayList<HotelRoom>();
        HotelRoom room = new HotelRoom(roomID, 100, notAvail, BedType.DOUBLE, false, true, true);
        oneRoomArray.add(room);

        ArrayList<Hotel> oneHotelArray = new ArrayList<Hotel>();
        Hotel hotel = new Hotel(hotelID, 5, 12, "Tristar Deluxe", "342 Exchange Hwy", "ATL", oneRoomArray);
        oneHotelArray.add(hotel);

        hotelList.setHotels(oneHotelArray);

        DataWriter.saveHotels();
        DataLoader.loadHotels();

        assertEquals(hotelID, HotelList.getHotels().get(0).getHotelID());
    }

    @Test
    public void testLoadZeroHotelsSize() throws IOException {
        ArrayList<Hotel> emptyHotelArray = new ArrayList<Hotel>();
        
        hotelList.setHotels(emptyHotelArray);

        DataWriter.saveHotels();
        DataLoader.loadHotels();

        assertEquals(0, HotelList.getHotels().size());
    }
}
