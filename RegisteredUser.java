import java.util.ArrayList;
import java.util.UUID;

import org.json.simple.JSONArray;

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

    public RegisteredUser(String userID, String firstName, String lastName, String address, String phoneNumber, 
    String username, String password, boolean passport, int age, String emailAddress, boolean frequentFlier, 
    JSONArray flightBooking, JSONArray hotelBooking){
        this.userID = UUID.fromString(userID);
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

    public UUID getUserID(){
       return this.userID;
    }

    public String getPhoneNumber(){
        return this.phoneNumber;
    }

    public String getUsername(){
        return this.username;
    }

    public String getPassword(){
        return this.password;
    }

    public boolean getPassport(){
        return this.passport;
    }

    public String getEmailAddress(){
        return this.emailAddress;
    }

    public String getAddress(){
        return this.address;
    }

    public boolean getFrequentFlyer(){
        return this.frequentFlyer;
    }

    public JSONArray getFlightBookingJSON(){
        return this.flightBookingJSON;
    }

    public JSONArray getHotelBookingJSON(){
        return this.hotelBookingJSON;
    }

    public ArrayList<FlightBooking> getFlightBookings() {
        return this.flightBookings;
    }

    public ArrayList<HotelBooking> getHotelBookings() {
        return this.hotelBookings;
    }

    public void setUserID(UUID userID) {
        this.userID = userID;
    }
    public void setPhoneNumber(String phoneNumber){
        this.phoneNumber = phoneNumber;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public void setPassport(boolean passport){
        this.passport = passport;
    }

    public void setEmailAddress(String emailAddress){
        this.emailAddress = emailAddress;
    }

    public void setAddress(String address){
        this.address = address;
    }

    public void setFrequentFlyer(boolean frequentFlyer){
        this.frequentFlyer = frequentFlyer;
    }

    @Override
    public String toString() {
        return this.firstName + " " + this.lastName;
    }

    public void setFlightBookings(ArrayList<FlightBooking> flightBookings) {
        this.flightBookings = flightBookings;
    }

    public void setHotelBookings(ArrayList<HotelBooking> hotelBookings) {
        this.hotelBookings = hotelBookings;
    }
}
