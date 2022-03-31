/**
 * @author Peyton Tucker, Avery Slomnicki, Austin Hanson
 * A class to represent a user's flight booking
 */
import java.util.ArrayList;
import java.util.UUID;
import java.io.PrintWriter;
import java.io.FileWriter;

public class FlightBooking {
    private UUID flightID;
    private ArrayList<Guest> guests;
    private int numCheckedBags;
    private int seatID;

    /**
     * Instantiates a new FlightBooking object with the given data
     * @param guests the guests that will accompany the user
     * @param flightID the flightID of the flight the user will take
     * @param seatID the seatID of the seat the user will sit in on the flight
     * @param numCheckedBags the user's number of checked bags
     */
    public FlightBooking(ArrayList<Guest> guests, UUID flightID, int seatID, int numCheckedBags){
        this.guests = guests;
        this.flightID = flightID;
        this.numCheckedBags = numCheckedBags;
        this.seatID = seatID;
    }

    /**
     * Get this FlightBooking's guests
     * @return this FlightBooking's guests
     */
    public ArrayList<Guest> getGuests(){
        return this.guests;
    }

    /**
     * Get this FlightBooking's checked bags
     * @return this FlightBooking's number of checked bags
     */
    public int getBags(){
        return this.numCheckedBags;
    }

    /**
     * Get this FlightBooking's seats
     * @return this FlightBooking's seats
     */
    public int getSeats(){
        return this.seatID;
    }

    /**
     * Get this FlightBooking's flightID
     * @return this FlightBooking's flightID
     */
    public UUID getFlightID() {
        return this.flightID;
    }

    /**
     * Set this object's number of guests
     * @param guests the guests to set
     */
    public void setGuests(ArrayList<Guest> guests){
        this.guests = guests;
    }

    /**
     * Set this object's number of checked bags
     * @param numCheckedBags the number of checked bags to set
     */
    public void setBags(int numCheckedBags){
        this.numCheckedBags = numCheckedBags;
    }

    /**
     * Set this object's seatID
     * @param seatID the seatID to set
     */
    public void setSeats(int seatID){
        this.seatID = seatID;
    }

    /**
     * set this object's flightID
     * @param flightID the flightID to set
     */
    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }
    
    /**
     * Set this object's guests
     * @param guests the ArrayList of guests to set
     */
    public void addGuests(ArrayList<Guest> guests){
        this.guests = guests;
    }

    /**
     * Generates a text file with this booking's information
     */
    public void printBooking() {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("bookings.txt", true)); //Opens file
            out.println(this.toString() + " \n");
            out.close(); //close the file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Produces a string with a neatly formatted list of this booking's guests
     * @return a String of guests
     */
    public String getListOfGuests(){
        String list = "";

        for (int i = 0; i < guests.size(); i++) {
            Guest guest = guests.get(i);
            list += i+1 + ") " + guest.getFirstName() + " " + guest.getLastName() + "\nSeatID: " + guest.getSeatID() + "\n";
        }
        if (list.length() == 0) return "None\n";
        return list;
    }

    /**
     * A toString method that converts this object into a human-readable String
     * @return the String which represents this object
     */
    @Override
    public String toString() {
        Flight flight = FlightList.getFlightByUUID(this.flightID);

        return "Flight Booking\n" + 
        flight.getDepartureCode() + " --> " + flight.getArrivalCode() +
        "\nFlight ID: " + this.flightID + 
        "\nSeat ID: " + this.seatID +
        "\nGuests:\n" + getListOfGuests() +
        "Checked Bags: " + this.numCheckedBags;
    }
}
