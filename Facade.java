/**
 * @author Peyton Tucker
 * A class used to interact with other classes within the program and to hold highly abstracted methods for use in the UserInterface class
 */

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

    /**
     * A private constructor for the Facade singleton. Initializes dataLoader, flightFilter, hotelFilter, flightList, hotelList, and userList
     */
    private Facade() {
        this.dataLoader = DataLoader.getInstance();
        this.flightFilter = new FlightFilter();
        this.hotelFilter = new HotelFilter();
        this.flightList = FlightList.getInstance();
        this.hotelList = HotelList.getInstance();
        this.userList = UserList.getInstance();
    }

    /**
     * Returns this Facade singleton
     * @return the Facade singleton
     */
    public static Facade getInstance() {
        if (facade == null) facade = new Facade();
        return facade;
    }

    /**
     * Loads flight bookings from a JSONArray into an ArrayList for the given RegisteredUser
     * @param user the RegisteredUser to load flight bookings for
     */
    public void loadUserFlightBookings(RegisteredUser user) {
        dataLoader.loadUserFlightBookings(user);
    }

    /**
     * Returns a user's flight bookings as an arraylist
     * @param user the RegisteredUser whose flight bookings are to be returned
     * @return an ArrayList of type FlightBooking of the user passed in
     */
    public ArrayList<FlightBooking> getUserFlightBookings(RegisteredUser user) {
        return user.getFlightBookings();
    }

    /**
     * Loads hotel bookings from a JSONArray into an ArrayList for the given RegisteredUser
     * @param user the RegisteredUser to load flight bookings for
     */
    public void loadUserHotelBookings(RegisteredUser user) {
        dataLoader.loadUserHotelBookings(user);
    }

    /**
     * Returns a user's hotel bookings as an arraylist
     * @param user The RegisteredUser object whose hotel bookings are to be returned
     * @return an ArrayList of type HotelBooking of the bookings belonging to the user passed in
     */
    public ArrayList<HotelBooking> getUserHotelBookings(RegisteredUser user) {
        return user.getHotelBookings();
    }

    /**
     * Creates a new RegisteredUser account/object with the information passed in and adds the new user to the UserList. Also sets currentUser to the new account created.
     * @param newUsername the username of the account to be created
     * @param newPassword the new account's password
     * @param firstName the first name of the user
     * @param lastName the last name of the user
     * @param age the age of the user
     * @param address the address of the user
     * @param phoneNumber the phone number of the user
     * @param emailAddress the email address of the user
     * @param passport the passport status of the user
     */
    public void createAccount(String newUsername, String newPassword, String firstName, String lastName, int age, String address, String phoneNumber, String emailAddress, boolean passport ){
        RegisteredUser newUser = new RegisteredUser(UUID.randomUUID(), firstName, lastName, address, phoneNumber, newUsername, newPassword, passport, age, emailAddress);
        userList.addUserToList(newUser);
        this.currentUser = newUser;
    }

    /**
     * Attempts to set the currentUser to the account whose credentials match the username and password passed in. Returns true if login is successful, false otherwise.
     * @param username the username of the account to log in with
     * @param password the password of the account to log in with
     * @return true if login is successful, false otherwise
     */
    public boolean logIn(String username, String password) {
        for (RegisteredUser user : userList.getUsers()) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                this.currentUser = user;
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the Facade's currentUser.
     * @return the RegisteredUser currently assigned to currentUser
     */
    public RegisteredUser getCurrentUser() {
        return this.currentUser;
    }

    /**
     * Sets the Facade's current user to null and saves the current list of User,s Hotels, and Flights to their respective JSON files.
     * @throws IOException if saving lists produces an error
     */
    public void logOut() throws IOException {
        currentUser = null;
        userList.saveUsers();
        hotelList.saveHotels();
        flightList.saveFlights();
    }

    /**
     * Returns the flight bookings of the currentUser.
     * @return an ArrayList of FlightBooking of the RegisteredUser currently assigned to currentUser.
     */
    public ArrayList<FlightBooking> viewFlightBookings() {
        if (currentUser.getFlightBookings() == null) loadUserFlightBookings(currentUser);
        return currentUser.getFlightBookings();
    }

    /**
     * Returns the flight bookings of the currentUser.
     * @return an ArrayList of HotelBooking of the RegisteredUser currently assigned to currentUser.
     */
    public ArrayList<HotelBooking> viewHotelBookings() {
        if (currentUser.getHotelBookings() == null) loadUserHotelBookings(currentUser);
        return currentUser.getHotelBookings();
    }

    /**
     * Calls the flightFilter singleton to search for flights with the provided departure and arrival codes.
     * @param departureCode
     * @param arrivalCode
     * @return
     */
    public ArrayList<Flight> searchForFlights(String departureCode, String arrivalCode) {
        return flightFilter.searchForFlights(departureCode, arrivalCode);
    }

    /**
     * Calls the hotelFilter singleton to search for hotels with the provided airport code.
     * @param airportCode the airport code for which hotels will be found
     * @return an arraylist of Hotels with the provided airport code
     */
    public ArrayList<Hotel> searchForHotels(String airportCode){
        return hotelFilter.searchFoHotels(airportCode);
    }

    /**
     * Calls the hotelFilter singleton to search for hotelRooms with the given airport code.
     * @param arrivalCode the airport code for which hotels will be found
     * @return an arraylist of HotelRooms with the provided airport code
     */
    public ArrayList<HotelRoom> searchForHotelRooms(String arrivalCode) {
        return hotelFilter.searchForHotelRooms(arrivalCode);
    }

    /**
     * Gets the currentUser's flight booking history.
     * @return an ArrayList of the currentUser's flight bookings.
     */
    public ArrayList<FlightBooking> getBookingHistory() {
        return currentUser.getFlightBookings();
    }

    /**
     * Gets the currentUser's hotel booking history.
     * @return an ArrayList of the currentUser's hotel bookings.
     */
    public ArrayList<HotelBooking> getHotelBookingHistory() {
        return currentUser.getHotelBookings();
    }

    /**
     * Filters a given ArrayList of flights according to the flight trait passed in.
     * @param flightTrait the flightTrait to filter flights by
     * @param unfilteredFlights the ArrayList of flights to be filtered
     * @return an ArrayList of filtered Flight objects
     */
    public ArrayList<Flight> filterFlights(FlightTrait flightTrait, ArrayList<Flight> unfilteredFlights) {
        return flightFilter.filterFlights(flightTrait, unfilteredFlights);
    }

    /**
     * Filters a given ArrayList of HotelRooms according to the bedType passed in.
     * @param bedType the bed type to filter hotels by
     * @param unfilteredHotelRooms the ArrayList of hotels to be filtered
     * @return an ArrayList of filtered Hotel objects
     */
    public ArrayList<HotelRoom> filterHotels(BedType bedType, ArrayList<HotelRoom> unfilteredHotelRooms) {
        return hotelFilter.filterHotelRooms(bedType, unfilteredHotelRooms);
    }

    /**
     * Determines whether or not a specific hotel room is completely available between a range of dates.
     * @param hotelID The hotelID of the room to determine availability for
     * @param roomID the roomID of the room to determien availability for
     * @param checkInDate the desired checkin date of the user booking a hotel
     * @param checkOutDate the desired checkout date of the user booking a hotel
     * @return true if the given room is not booked on any of the dates in the provided date range
     */
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

    /**
     * A utility method to get an arraylist of dates between a start date and an end date
     * @param checkIn the desired date of checkin, serving as the starting date of the date range
     * @param checkOut the desired date of checkout, serving as the ending date of the date range
     * @return an ArrayList of type LocalDate of dates between the two given dates
     */
    public ArrayList<LocalDate> getDaysBetweenDates(LocalDate checkIn, LocalDate checkOut) {
        ArrayList<LocalDate> dates = new ArrayList<LocalDate>();
        
        Period periodBetweenDates = Period.between(checkIn, checkOut);
        for (int i = 0; i < periodBetweenDates.getDays()+1; i++) {
            dates.add(checkIn.plusDays(i));
        }

        return dates;
    }
    
    /**
     * Adds a given hotel room to a user's list of hotel bookings, and updates the hotel room's list of notAvailable dates.
     * @param hotelID the hotelID of the room to book
     * @param roomID the roomID of the room to book
     * @param checkInDate the desired checkIn date of the user
     * @param checkOutDate the desired checkOut date of the user
     * @return true if the booking was successful, and false otherwise
     */
    public boolean bookHotelRoom(UUID hotelID, int roomID, LocalDate checkInDate, LocalDate checkOutDate) {
        ArrayList<HotelBooking> currentUserBookings = currentUser.getHotelBookings();
        ArrayList<LocalDate> requestedDates = getDaysBetweenDates(checkInDate, checkOutDate);

        if (roomIsAvailableBetweenDates(hotelID, roomID, checkInDate, checkOutDate)) {
            HotelBooking booking = new HotelBooking(hotelID, roomID, requestedDates);
            currentUserBookings.add(booking);

            currentUser.setHotelBookings(currentUserBookings);

            HotelList.getHotelByUUID(hotelID).getRoomByID(roomID).addToNotAvail(requestedDates);
            return true;
        }
        return false;
    }

    /**
     * Adds a given flight to a user's list of flight bookings, and updates the seats on the flight to ensure their isBooked boolean is true
     * @param booking the flightBooking to add to a user's list of flight bookings
     */
    public void bookFlight(FlightBooking booking) {
            ArrayList<FlightBooking> currentUserBookings = currentUser.getFlightBookings();
            currentUserBookings.add(booking);
            
            Flight bookingFlight = FlightList.getFlightByUUID(booking.getFlightID());
            bookingFlight.getSeatByID(booking.getSeats()).setIsBooked(true); //set user's flight seat to booked

            ArrayList<Guest> guests = booking.getGuests(); //set each guest's seat to booked
            for (Guest guest : guests) {
                bookingFlight.getSeatByID(guest.getSeatID()).setIsBooked(true); 
            }

            currentUser.setFlightBookings(currentUserBookings);
    }
}