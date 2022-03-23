import java.util.ArrayList;
import java.util.UUID;

public class Facade {
    private static Facade facade;
    private UserList userList;
    private FlightList flightList;
    private HotelList hotelList;
    private DataLoader dataLoader;
    private DataWriter dataWriter;
    private User currentUser;


    private Facade() {
        this.userList = UserList.getInstance();
        this.flightList = FlightList.getInstance();
        this.hotelList = HotelList.getInstance();
        this.dataLoader = DataLoader.getInstance();
    }

    public static Facade getInstance() {
        if (facade == null) facade = new Facade();
        return facade;
    }

    public void loadAllUsers() {
        userList.setUsers(dataLoader.loadUsers());
    }

    public void loadAllFlights() {
        flightList.setFlights(dataLoader.loadFlights());

    }

    public RegisteredUser getRegisteredUserByUUID(UUID uuid) {
        return UserList.getUserByUUID(uuid);
    }

    public void parseUserFlightBookings(RegisteredUser user) {
        dataLoader.loadUserFlightBookings(user);
    }

    public ArrayList<FlightBooking> getUserFlightBookings(RegisteredUser user) {
        return user.getFlightBookings();
    }

    public void parseUserHotelBookings(RegisteredUser user) {
        dataLoader.loadUserHotelBookings(user);
    }

    public ArrayList<HotelBooking> getUserHotelBookings(RegisteredUser user) {
        return user.getHotelBookings();
    }

    public Flight getFlightByUUID(UUID uuid) {
        return flightList.getFlightByUUID(uuid);
    }

    public Hotel getHotelByUUID(UUID uuid) {
        return null;
    }

    public void logIn(String username, String password) {

    }

    public void logOut() {
        currentUser = null;
    }

    public ArrayList<FlightBooking> viewFlightBookings() {
        return null;
    }

    public ArrayList<HotelBooking> viewHotelBookings() {
        return null;
    }

    public ArrayList<Flight> searchForFlight(String departureCode, String arrivalCode) {
        return null;
    }

    public ArrayList<Hotel> searchForHotel(String arrivalCode) {
        return null;
    }

}