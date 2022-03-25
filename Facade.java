import java.util.ArrayList;
import java.util.UUID;

public class Facade {
    private static Facade facade;
    private UserList userList;
    private FlightList flightList;
    private HotelList hotelList;
    private DataLoader dataLoader;
    private DataWriter dataWriter;
    private User registeredUser;


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

    public void loadUserFlightBookings(RegisteredUser user) {
        dataLoader.loadUserFlightBookings(user);
    }

    public ArrayList<FlightBooking> getUserFlightBookings(RegisteredUser user) {
        return user.getFlightBookings();
    }

    public void loadUserHotelBookings(RegisteredUser user) {
        dataLoader.loadUserHotelBookings(user);
    }

    public ArrayList<HotelBooking> getUserHotelBookings(RegisteredUser user) {
        return user.getHotelBookings();
    }

    public void createAccount(String newUsername, String newPassword, String firstName, String lastName, int age, String address, String phoneNumber, String emailAddress, boolean passport ){

    }

    public void logIn(String username, String password) {

    }

    public void loginError(){
        return;
    }

    public void guestError(){
        return;
    }


    public void mainMenu(){
        return;
    }
    public void searchForFlight(boolean roundTrip, String departingCode, String arrivalCode, int passengers, int carryOn, int checked){

    }

    public void logOut() {
        registeredUser = null;
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