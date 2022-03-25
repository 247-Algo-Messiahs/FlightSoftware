import java.util.ArrayList;
import java.util.UUID;

public class Facade {
    private static Facade facade;
    private UserList userList;
    private FlightList flightList;
    private HotelList hotelList;
    private DataLoader dataLoader;
    private DataWriter dataWriter;
    private FlightFilter flightFilter;
    private HotelFilter hotelFilter;
    private RegisteredUser currentUser;


    private Facade() {
        this.userList = UserList.getInstance();
        this.flightList = FlightList.getInstance();
        this.hotelList = HotelList.getInstance();
        this.dataLoader = DataLoader.getInstance();
        this.flightFilter = new FlightFilter();
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

    //returns 1 if login successful
    //returns 0 if username and password match not found
    public int logIn(String username, String password) {
        for (RegisteredUser user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.currentUser = user;
                return 1;
            }
        }
        return 0;
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
        currentUser = null;
    }

    public ArrayList<FlightBooking> viewFlightBookings() {
        if (currentUser.getFlightBookings() == null) loadUserFlightBookings(currentUser);
        return currentUser.getFlightBookings();
    }

    public ArrayList<HotelBooking> viewHotelBookings() {
        if (currentUser.getHotelBookings() == null) loadUserHotelBookings(currentUser);
        return currentUser.getHotelBookings();
    }

    public ArrayList<Flight> searchForFlights(String departureCode, String arrivalCode) {
        return flightFilter.searchForFlights(departureCode, arrivalCode);
    }

    public ArrayList<HotelRoom> searchForHotelRooms(String arrivalCode) {
        return hotelFilter.searchForHotelRooms(arrivalCode);
    }

}