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
        this.dataWriter = DataWriter.getInstance();
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

    public User getUserByUUID(UUID uuid) {
        return userList.getUserByUUID(uuid);
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

    public Preferences viewPreferences() {
        return currentUser.getPreferences();
    }

    public void updatePreferences(Preferences preferences) {
        currentUser.setPreferences(preferences);
    }

    public void giveQuestionnaire() {

    }

    public ArrayList<Flight> searchForFlight(Preferences pref) {
        return null;
    }

    public ArrayList<Flight> searchForFlight(String departureCode, String arrivalCode) {
        return null;
    }

    public ArrayList<Hotel> searchForHotel(String arrivalCode) {
        return null;
    }

    public ArrayList<Hotel> searchForHotel(String arrivalCode, Preferences pref) {
        return null;
    }
}