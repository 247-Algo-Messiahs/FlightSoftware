import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;


public class FacadeTest {

    public DataLoader dataLoader = DataLoader.getInstance();
    public Facade facade = Facade.getInstance();
    public UserList userList = UserList.getInstance();
    public ArrayList<RegisteredUser> allUsers = userList.getUsers();
    public FlightList flightList = FlightList.getInstance();
    public ArrayList<Flight> allFlights = FlightList.getFlights();
    public HotelList hotelList = HotelList.getInstance();
    public ArrayList<Hotel> allHotels = HotelList.getHotels();

    @BeforeEach
    public void setup() {
        ArrayList<RegisteredUser> blankUsers = new ArrayList<RegisteredUser>();
        userList.setUsers(blankUsers);
        
        ArrayList<Hotel> blankHotels = new ArrayList<Hotel>();
        hotelList.setHotels(blankHotels);

        ArrayList<Flight> blankFlights = new ArrayList<Flight>();
        flightList.setFlights(blankFlights);
    }

    @AfterEach
    public void tearDown() {
        allUsers.clear();
        allFlights.clear();
        allHotels.clear();
    }

    @Test
    public void testCreateNewAccount() {
        ArrayList<RegisteredUser> blankUsers = new ArrayList<RegisteredUser>();
        userList.setUsers(blankUsers);

        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);

        assertEquals("billygreen", userList.getUsers().get(0).getUsername());
    }

    //expected to fail - did not take into consideration when completing project
    @Test
    public void testCreateDuplicateAccount() {
        ArrayList<RegisteredUser> blankUsers = new ArrayList<RegisteredUser>();
        userList.setUsers(blankUsers);

        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);
        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);

        assertNull(userList.getUsers().get(1).getUsername());
    }

    @Test
    public void testLogIn() {
        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);

        facade.logIn("billygreen", "billyg123");

        assertEquals("billygreen", facade.getCurrentUser().getUsername());
    }

    @Test
    public void testLogOut() throws IOException {
        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);
        facade.logIn("billygreen", "billyg123");

        facade.logOut();

        assertNull(facade.getCurrentUser());
    }

    @Test
    public void testRoomIsNotAvailableBetweenDates() {
        ArrayList<LocalDate> notAvailDate = new ArrayList<LocalDate>();
        LocalDate date = LocalDate.parse("2023-01-01");
        notAvailDate.add(date);

        ArrayList<HotelRoom> oneRoom = new ArrayList<HotelRoom>();
        oneRoom.add(new HotelRoom(1, 100, notAvailDate, BedType.DOUBLE, false, true, true));
        
        ArrayList<Hotel> oneHotel = new ArrayList<Hotel>();
        UUID hotelID = UUID.randomUUID();
        oneHotel.add(new Hotel(hotelID, 5, 12, "Test Hotel Deluxe", "543 Hotel Blvd", "ATL", oneRoom));

        hotelList.setHotels(oneHotel);

        assertFalse(facade.roomIsAvailableBetweenDates(hotelID, 1, LocalDate.parse("2023-01-01"), LocalDate.parse("2023-01-02")));
    }

    @Test
    public void testRoomIsAvailableBetweenDates() {
        ArrayList<LocalDate> notAvailDate = new ArrayList<LocalDate>();
        LocalDate date1 = LocalDate.parse("2023-01-01");
        LocalDate date2 = LocalDate.parse("2023-01-04");
        notAvailDate.add(date1);
        notAvailDate.add(date2);

        ArrayList<HotelRoom> oneRoom = new ArrayList<HotelRoom>();
        oneRoom.add(new HotelRoom(1, 100, notAvailDate, BedType.DOUBLE, false, true, true));
        
        ArrayList<Hotel> oneHotel = new ArrayList<Hotel>();
        UUID hotelID = UUID.randomUUID();
        oneHotel.add(new Hotel(hotelID, 5, 12, "Test Hotel Deluxe", "543 Hotel Blvd", "ATL", oneRoom));

        hotelList.setHotels(oneHotel);

        assertTrue(facade.roomIsAvailableBetweenDates(hotelID, 1, LocalDate.parse("2023-01-02"), LocalDate.parse("2023-01-03")));
    }

    @Test
    public void testGetDaysBetweenDates() {
        LocalDate checkInDate = LocalDate.parse("2023-01-01");
        LocalDate checkOutDate = LocalDate.parse("2023-01-03");

        ArrayList<LocalDate> allDatesOfStay = new ArrayList<LocalDate>();
        allDatesOfStay.add(LocalDate.parse("2023-01-01"));
        allDatesOfStay.add(LocalDate.parse("2023-01-02"));
        allDatesOfStay.add(LocalDate.parse("2023-01-03"));

        assertEquals(allDatesOfStay, facade.getDaysBetweenDates(checkInDate, checkOutDate));
    }

    @Test
    public void testBookHotelRoom() {
        LocalDate checkInDate = LocalDate.parse("2023-01-02");
        LocalDate checkOutDate = LocalDate.parse("2023-01-03");

        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);
        facade.logIn("billygreen", "billyg123");

        ArrayList<LocalDate> notAvailDate = new ArrayList<LocalDate>();
        LocalDate date1 = LocalDate.parse("2023-01-01");
        LocalDate date2 = LocalDate.parse("2023-01-04");
        notAvailDate.add(date1);
        notAvailDate.add(date2);

        ArrayList<HotelRoom> oneRoom = new ArrayList<HotelRoom>();
        oneRoom.add(new HotelRoom(1, 100, notAvailDate, BedType.DOUBLE, false, true, true));
        
        ArrayList<Hotel> oneHotel = new ArrayList<Hotel>();
        UUID hotelID = UUID.randomUUID();
        oneHotel.add(new Hotel(hotelID, 5, 12, "Test Hotel Deluxe", "543 Hotel Blvd", "ATL", oneRoom));

        hotelList.setHotels(oneHotel);

        assertTrue(facade.bookHotelRoom(hotelID, 1, checkInDate, checkOutDate));
    }

    @Test
    public void testBookNotAvailHotelRoom() {
        LocalDate checkInDate = LocalDate.parse("2023-01-02");
        LocalDate checkOutDate = LocalDate.parse("2023-01-03");

        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);
        facade.logIn("billygreen", "billyg123");

        ArrayList<LocalDate> notAvailDate = new ArrayList<LocalDate>();
        LocalDate date1 = LocalDate.parse("2023-01-02");
        LocalDate date2 = LocalDate.parse("2023-01-04");
        notAvailDate.add(date1);
        notAvailDate.add(date2);

        ArrayList<HotelRoom> oneRoom = new ArrayList<HotelRoom>();
        oneRoom.add(new HotelRoom(1, 100, notAvailDate, BedType.DOUBLE, false, true, true));
        
        ArrayList<Hotel> oneHotel = new ArrayList<Hotel>();
        UUID hotelID = UUID.randomUUID();
        oneHotel.add(new Hotel(hotelID, 5, 12, "Test Hotel Deluxe", "543 Hotel Blvd", "ATL", oneRoom));

        hotelList.setHotels(oneHotel);

        assertFalse(facade.bookHotelRoom(hotelID, 1, checkInDate, checkOutDate));
    }

    @Test
    public void testBookFlight() {
        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);
        facade.logIn("billygreen", "billyg123");

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

        facade.bookFlight(flightBooking);

        assertEquals(seatID, facade.getCurrentUser().getFlightBookings().get(0).getSeats());
    }

    //expected to fail - isBooked boolean for Seat does exist, but was not utilized correctly to prevent duplicate seat bookings in facade.bookFlight()
    @Test
    public void testBookFullSeat() {
        facade.createAccount("billygreen", "billyg123", "Billy", "Green", 35, "120 Stockhead Drive", "555-123-1234", "billyg@gmail.com", true);
        facade.logIn("billygreen", "billyg123");

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

        facade.bookFlight(flightBooking);
        //attempt to book same seat on same flight again
        facade.bookFlight(flightBooking);

        assertNull(facade.getCurrentUser().getFlightBookings().get(1).getSeats());
    }
}