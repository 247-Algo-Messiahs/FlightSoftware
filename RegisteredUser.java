import java.util.ArrayList;
import java.util.UUID;

public class RegisteredUser {
    private UUID userID;
    private String phoneNumber;
    private String username;
    private String password;
    private boolean passport;
    private String emailAddress;
    private String address;
    private Preferences preferences;
    private boolean frequentFlyer;
    private ArrayList<FlightBooking> flightHistory;
    private ArrayList<HotelBooking> hotelHistory;

    public RegisteredUser(String firstName, String lastName, String address, String phoneNumber, String username, String password, boolean passport, int age, String emailAddress){

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

    public Preferences getPreferences(){
        return this.preferences;
    }

    public boolean getFrequentFlyer(){
        return this.frequentFlyer;
    }

    public ArrayList<FlightBooking> getFlightHistory(){
        return this.flightHistory;
    }

    public ArrayList<HotelBooking> getHotelHistory(){
        return this.hotelHistory;
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

    public void setPreferences(Preferences preferences){
        this.preferences = preferences;
    }

    public void setFrequentFlyer(boolean frequentFlyer){
        this.frequentFlyer = frequentFlyer;
    }

    public void setFlightHistory(ArrayList<FlightBooking> flightHistory){
        this.flightHistory = flightHistory;
    }

    public void setHotelHistory(ArrayList<HotelBooking> hotelHistory){
        this.hotelHistory = hotelHistory;
    }
    


}
