import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;
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
    private FlightBooking currentFlightBooking;
    private HotelBooking currentHotelBooking;


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
        RegisteredUser newUser = new RegisteredUser(UUID.randomUUID(), firstName, lastName, address, phoneNumber, newUsername, newPassword, passport, age, emailAddress);
        userList.addUserToList(newUser);
        this.currentUser = newUser;
    }

    //returns 1 if login successful
    //returns 0 if username and password match not found
    public boolean logIn(String username, String password) {
        for (RegisteredUser user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.currentUser = user;
                return true;
            }
        }
        return false;
    }

    public RegisteredUser getCurrentUser() {
        return this.currentUser;
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

    public void logOut() throws IOException {
        currentUser = null;
        userList.saveUsers();
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

    public ArrayList<FlightBooking> getBookingHistory() {
        return currentUser.getFlightBookings();
    }

    public ArrayList<Flight> filterFlights(FlightTrait flightTrait, ArrayList<Flight> unfilteredFlights) {
        return flightFilter.filterFlights(flightTrait, unfilteredFlights);
    }

    public ArrayList<HotelRoom> filterHotels(BedType bedType, ArrayList<HotelRoom> unfilteredHotelRooms) {
        return hotelFilter.filterHotelRooms(bedType, unfilteredHotelRooms);
    }

    public boolean roomIsAvailableBetweenDates(UUID hotelID, int roomID, LocalDate checkInDate, LocalDate checkOutDate) {
        ArrayList<LocalDate> datesToBook = getDaysBetweenDates(checkInDate, checkOutDate);
        ArrayList<LocalDate> notAvailDates = HotelList.getHotelByUUID(hotelID).getRoomByID(roomID).getNotAvail();

        for (int i = 0; i < datesToBook.size(); i++) {
            for (int k = 0; k < notAvailDates.size(); k++) {
                if (datesToBook.get(i).equals(notAvailDates.get(k))) return false;
            }
        }
        
        return true;
    }

    public ArrayList<LocalDate> getDaysBetweenDates(LocalDate checkIn, LocalDate checkOut) {
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        
        Period periodBetweenDates = Period.between(checkIn, checkOut);
        for (int i = 0; i < periodBetweenDates.getDays()+1; i++) {
            dates.add(checkIn.plusDays(i));
        }

        return dates;
    }
    
    //returns 1 if booking was successful, returns 0 if room was not available at requested dates
    public int bookHotelRoom(UUID hotelID, int roomID, LocalDate checkInDate, LocalDate checkOutDate) {
        ArrayList<HotelBooking> currentUserBookings = currentUser.getHotelBookings();
        ArrayList<LocalDate> requestedDates = getDaysBetweenDates(checkInDate, checkOutDate);

        if (roomIsAvailableBetweenDates(hotelID, roomID, checkInDate, checkOutDate)) {
            HotelBooking booking = new HotelBooking(hotelID, roomID, requestedDates);
            currentUserBookings.add(booking);

            currentUser.setHotelBookings(currentUserBookings);

            HotelList.getHotelByUUID(hotelID).getRoomByID(roomID).addToNotAvail(requestedDates);
            return 1;
        }
        return 0;
    }

    //returns true if flight was successfully booked; returns false if it was not, likely due to seat already being booked
    public boolean bookFlight(ArrayList<Guest> guests, UUID flightID, int seatID, int numCheckedBags) {
        Seat requestedSeat = FlightList.getFlightByUUID(flightID).getSeatByID(seatID);
        
        if (!requestedSeat.getIsBooked()) {
            ArrayList<FlightBooking> currentUserBookings = currentUser.getFlightBookings();
            
            FlightBooking booking = new FlightBooking(guests, flightID, seatID, numCheckedBags);
            currentUserBookings.add(booking);
            
            requestedSeat.setIsBooked(true);

            currentUser.setFlightBookings(currentUserBookings);

            return true;
        }
        return false;
    }
}