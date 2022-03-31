import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;

/**
 * @author Nicolas Becker, Peyton Tucker, Avery Slomnicki, Austin Hanson
 */
public class RegisteredUser extends User{
    private UUID userID;
    private String phoneNumber;
    private String username;
    private String password;
    private boolean passport;
    private String emailAddress;
    private String address;
    private boolean frequentFlyer;
    private JSONArray flightBookingJSON;
    private JSONArray hotelBookingJSON;
    private ArrayList<FlightBooking> flightBookings;
    private ArrayList<HotelBooking> hotelBookings;

    /**
     * Defines RegisteredUser object
     * 
     * for use in populating UserList from users.json
     * @param userID
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param username
     * @param password
     * @param passport
     * @param age
     * @param emailAddress
     * @param frequentFlier
     * @param flightBooking
     * @param hotelBooking
     */
    public RegisteredUser(UUID userID, String firstName, String lastName, String address, String phoneNumber, 
    String username, String password, boolean passport, int age, String emailAddress, boolean frequentFlier, 
    JSONArray flightBooking, JSONArray hotelBooking){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.passport = passport;
        this.age = age;
        this.emailAddress = emailAddress;
        this.frequentFlyer = frequentFlier;
        this.flightBookingJSON = flightBooking;
        this.hotelBookingJSON = hotelBooking;
        flightBookings = new ArrayList<FlightBooking>();
        hotelBookings = new ArrayList<HotelBooking>();
    }

    /**
     * Defines RegisteredUser
     * 
     * for use in creating new RegisteredUser accounts
     * @param userID
     * @param firstName
     * @param lastName
     * @param address
     * @param phoneNumber
     * @param username
     * @param password
     * @param passport
     * @param age
     * @param emailAddress
     */
    public RegisteredUser(UUID userID, String firstName, String lastName, String address, String phoneNumber, 
    String username, String password, boolean passport, int age, String emailAddress){
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.passport = passport;
        this.age = age;
        this.emailAddress = emailAddress;
        flightBookings = new ArrayList<FlightBooking>();
        hotelBookings = new ArrayList<HotelBooking>();
    }

    /**
     * 
     * @return UUID of given userID
     */
    public UUID getUserID(){
       return this.userID;
    }

    /**
     * 
     * @return String of given phone number
     */
    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    /**
     * 
     * @return String of given username
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * 
     * @return String of given password
     */
    public String getPassword(){
        return this.password;
    }

    /**
     * 
     * @return boolean of given passport
     */
    public boolean getPassport(){
        return this.passport;
    }

    /**
     * 
     * @return String of given email address
     */
    public String getEmailAddress(){
        return this.emailAddress;
    }

    /**
     * 
     * @return String of given address
     */
    public String getAddress(){
        return this.address;
    }

    /**
     * 
     * @return boolean of if user is a frequent flyer
     */
    public boolean getFrequentFlyer(){
        return this.frequentFlyer;
    }

    /**
     * 
     * @return JSONArray of given flightBooking
     */
    public JSONArray getFlightBookingJSON(){
        return this.flightBookingJSON;
    }

    /**
     * 
     * @return JSONArray of given hotelBooking
     */
    public JSONArray getHotelBookingJSON(){
        return this.hotelBookingJSON;
    }

    /**
     * 
     * @return ArrayList<FlightBooking> of given flightBookings
     */
    public ArrayList<FlightBooking> getFlightBookings() {
        return this.flightBookings;
    }

    /**
     * 
     * @return ArrayList<HotelBooking> of given hotelBookings
     */
    public ArrayList<HotelBooking> getHotelBookings() {
        return this.hotelBookings;
    }

    /**
     * Sets given userID
     * @param userID
     */
    public void setUserID(UUID userID) {
        this.userID = userID;
    }

    /**
     * Sets given phone number
     * @param phoneNumber
     */
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    /**
     * Sets given username
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * Sets given password
     * @param password
     */
    public void setPassword(String password){
        this.password = password;
    }

    /**
     * Sets if a user has a passport
     * @param passport
     */
    public void setPassport(boolean passport){
        this.passport = passport;
    }

    /**
     * Sets a user's email address
     * @param emailAddress
     */
    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    /**
     * Sets given address
     * @param address
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Sets boolean of if user is a frequent flyer
     * @param frequentFlyer
     */
    public void setFrequentFlyer(boolean frequentFlyer){
        this.frequentFlyer = frequentFlyer;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    /**
     * Sets given flightBookings with ArrayList<FlightBooking>
     * @param flightBookings
     */
    public void setFlightBookings(ArrayList<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }

    /**
     * Sets given hotelBookings with ArrayList<HotelBooking>
     * @param hotelBookings
     */
    public void setHotelBookings(ArrayList<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }
}
