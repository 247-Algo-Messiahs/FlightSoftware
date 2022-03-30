import java.util.ArrayList;
import java.util.UUID;
import java.io.PrintWriter;
import java.io.FileWriter;

public class FlightBooking {
    private UUID flightID;
    private ArrayList<Guest> guests;
    private int numCheckedBags;
    private int seatID;

    public FlightBooking(ArrayList<Guest> guests, UUID flightID, int seatID, int numCheckedBags){
        this.guests = guests;
        this.flightID = flightID;
        this.numCheckedBags = numCheckedBags;
        this.seatID = seatID;
    }

    public ArrayList<Guest> getGuests(){
        return this.guests;
    }

    public int getBags(){
        return this.numCheckedBags;
    }

    public int getSeats(){
        return this.seatID;
    }

    public UUID getFlightID() {
        return this.flightID;
    }

    public void setGuests(ArrayList<Guest> guests){
        this.guests = guests;
    }

    public void setBags(int numCheckedBags){
        this.numCheckedBags = numCheckedBags;
    }

    public void setSeats(int seatID){
        this.seatID = seatID;
    }

    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
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

    public String getListOfGuests(){
        String list = "";

        for (int i = 0; i < guests.size(); i++) {
            Guest guest = guests.get(i);
            list += i+1 + ") " + guest.getFirstName() + " " + guest.getLastName() + "\nSeatID: " + guest.getSeatID() + "\n";
        }
        if (list.length() == 0) return "None\n";
        return list;
    }

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
