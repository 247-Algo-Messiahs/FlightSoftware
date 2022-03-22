import java.util.ArrayList;
import java.util.UUID;

public class FlightBooking {
    private UUID userID;
    private RegisteredUser user;
    private ArrayList<Flight> flights;
    private ArrayList<Guest> guests;
    private int numCheckedBags;
    private ArrayList<UUID> seatID;

    public FlightBooking(UUID userID, RegisteredUser user, ArrayList<Flight> flight, ArrayList<Guest> guests, int numCheckedBags, ArrayList<UUID> seatID){

    }

    public UUID getUserID(){
        return this.userID;
    }

    public RegisteredUser getRegisteredUser(){
        return this.user;
    }

    public ArrayList<Flight> getFlights(){
        return this.flights;
    }

    public ArrayList<Guest> getGuests(){
        return this.guests;
    }

    public int getBags(){
        return this.numCheckedBags;
    }

    public ArrayList<UUID> getSeats(){
        return this.seatID;
    }

    public void setUserID(UUID userID){
        this.userID = userID;
    }

    public void setRegisteredUser(RegisteredUser user){
        this.user = user;
    }

    public void setFlights(ArrayList<Flight> flights){
        this.flights = flights;
    }

    public void setGuests(ArrayList<Guest> guests){
        this.guests = guests;
    }

    public void setBags(int numCheckedBags){
        this.numCheckedBags = numCheckedBags;
    }

    public void setSeats(ArrayList<UUID> seatID){
        this.seatID = seatID;
    }

    

    public void addGuests(ArrayList<Guest> guests){
        this.guests = guests;
    }

    public void assignSeats(){

    }

    public void pickFlights(){

    }

   

    public void confirmBooking(){

    }

    public void displayBooking(){
        
    }
    
}
