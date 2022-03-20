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
        this.userList = userList.getInstance();
        this.flightList = flightList.getInstance();
        this.hotelList = hotelList.getInstance();
        this.dataLoader = dataLoader.getInstance();
        this.dataWriter = dataWriter.getInstance();
    }

    public static Facade getInstance() {
        if (facade == null) facade = new Facade();
        return facade;
    }

    public User getUserByUUID(UUID uuid) {
        return null;
    }

    public Flight getFlightByUUID(UUID uuid) {
        return null;
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