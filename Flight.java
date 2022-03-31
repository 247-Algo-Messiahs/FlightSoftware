/**
 * @author Peyton Tucker, Avery Slomnicki, Nico Becker, Austin Hanson
 */

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;
import org.json.simple.JSONArray;

public class Flight {
    private UUID flightID;
    private String departureCode;
    private String arrivalCode;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivalTime;
    private int numAvailFirstSeats;
    private int numAvailBusinessSeats;
    private int numAvailEconomySeats;
    private boolean isFull;
    private boolean isInternational;
    private ArrayList<Seat> seats;
    private int price;
    private JSONArray connectionsJSON;
    private ArrayList<Flight> connections;
    

    /**
     * Constructs a new Flight object.
     * @param flightID the flight's flightID
     * @param departureCode the flight's departure airport code
     * @param arrivalCode the flight's arrival airport code
     * @param departureTime the departure time of this flight
     * @param arrivalTime teh arrival time of this flight
     * @param numAvailFirstSeats the number of available first class seats of this flight
     * @param numAvailBusinessSeats the number of available business seats of this flight
     * @param numAvailEconomySeats the number of available economy seats of this flight
     * @param isFull the full status of this flight
     * @param isInternational the international status of this flight
     * @param seats the seats of this flight
     * @param price the price of this flight
     * @param connectionsJSON a JSONArray of connecting flights
     */
    public Flight(UUID flightID, String departureCode, String arrivalCode, LocalTime departureTime, LocalTime arrivalTime, int numAvailFirstSeats,
     int numAvailBusinessSeats, int numAvailEconomySeats, boolean isFull, boolean isInternational, ArrayList<Seat> seats, int price, JSONArray connectionsJSON) {
        this.flightID = flightID;
        this.departureCode = departureCode;
        this.arrivalCode = arrivalCode;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.numAvailFirstSeats = numAvailFirstSeats;
        this.numAvailBusinessSeats = numAvailBusinessSeats;
        this.numAvailEconomySeats = numAvailEconomySeats;
        this.isFull = isFull;
        this.isInternational = isInternational;
        this.seats = seats;
        this.price = price;
        this.connectionsJSON = connectionsJSON;
        this.connections = new ArrayList<Flight>();
    }

    /**
     * Second constructor, unused currently, for future functionality including dates of departure and arrival
     * @param flightID the flight's flightID
     * @param departureCode the flight's departure code
     * @param arrivalCode the flight's arrival code
     * @param departureTime the flight's departure time
     * @param departureDate the flight's departure date
     * @param arrivalTime the flight's arrival time
     * @param arrivalDate the flight's arrival date
     * @param numAvailFirstSeats the number of available first class seats
     * @param numAvailBusinessSeats the number of available business class seats
     * @param numAvailEconomySeats the number of available economy class seats
     * @param isFull the full status of the flight
     * @param isInternational the international status of the flight
     */
    public Flight(String flightID, String departureCode, String arrivalCode, LocalTime departureTime, LocalDate departureDate, LocalTime arrivalTime, LocalDate arrivalDate,
     int numAvailFirstSeats, int numAvailBusinessSeats, int numAvailEconomySeats, boolean isFull, boolean isInternational) {
        this.flightID = UUID.fromString(flightID);
        this.departureCode = departureCode;
        this.arrivalCode = arrivalCode;
        this.departureTime = departureTime;
        this.departureDate = departureDate;
        this.arrivalTime = arrivalTime;
        this.arrivalDate = arrivalDate;
        this.numAvailFirstSeats = numAvailFirstSeats;
        this.numAvailBusinessSeats = numAvailBusinessSeats;
        this.numAvailEconomySeats = numAvailEconomySeats;
        this.isFull = isFull;
        this.isInternational = isInternational;
    }

    /**
     * Returns the full status of this flight
     * @return the full status of this flight
     */
    public boolean getIsFull() {
        return this.isFull;
    }

    /**
     * Returns this flight's flightID
     * @return the UUId of this flight
     */
    public UUID getFlightID() {
        return this.flightID;
    }

    /**
     * Returns this flight's departure code
     * @return this flight's departure code
     */
    public String getDepartureCode() {
        return this.departureCode;
    }

    /**
     * Returns this flight's arrival code
     * @return this flight's arrival code
     */
    public String getArrivalCode() {
        return this.arrivalCode;
    }

    /**
     * Returns this flight's departure date
     * @return this flight's departure date
     */
    public LocalDate getDepartureDate() {
        return this.departureDate;
    }

    /**
     * Returns this flight's departure time
     * @return this flight's departure time
     */
    public LocalTime getDepartureTime() {
        return this.departureTime;
    }

    /**
     * Returns this flight's arrival date
     * @return this flight's arrival date
     */
    public LocalDate getArrivalDate() {
        return this.arrivalDate;
    }

    /**
     * Returns this flight's arrival time
     * @return this flight's arrival time
     */
    public LocalTime getArrivalTime() {
        return this.arrivalTime;
    }

    /**
     * Returns this flight's duration
     * @return this flight's duration
     */
    public int getDuration(){
        int duration = (int)Duration.between(this.departureTime, this.arrivalTime).toMinutes();
        if (duration < 0) duration = 1440 + duration; //accounts for if flight leaves in p.m., and lands in a.m.

        return duration;
    }

    /**
     * Returns this flight's number of available first class seats
     * @return this flight's number of available first class seats
     */
    public int getNumAvailFirstSeats() {
        return this.numAvailFirstSeats;
    }

    /**
     * Returns this flight's number of available business seats
     * @return this flight's number of available business seats
     */
    public int getNumBusinessSeats() {
        return this.numAvailBusinessSeats;
    }

    /**
     * Returns this flight's number of economy seats
     * @return this flight's number of economy seats
     */
    public int getNumEconomySeats() {
        return this.numAvailEconomySeats;
    }

    /**
     * Returns this flight's international status
     * @return this flight's international status
     */
    public boolean getIsInternational() {
        return this.isInternational;
    }

    /**
     * Returns this flight's seats
     * @return this flight's seats
     */
    public ArrayList<Seat> getSeats() {
        return this.seats;
    }

    /**
     * Returns this flight's price
     * @return this flight's price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * Returns a seat whose seat number matches the String provided
     * @param seatNum The seat number of the seat to find
     * @return the Seat object with the provided seat number
     */
    public Seat getSeatBySeatNumber(String seatNum) {
        for (Seat seat : this.seats) {
            if (seat.getSeatNumber().equals(seatNum)) return seat;
        }
        return null;
    }

    /**
     * Returns this flight's JSONArray of connections
     * @return this flight's connections JSONArray
     */
    public JSONArray getConnectionsJSON() {
        return this.connectionsJSON;
    }

    /**
     * Calculates the duration of the total trip if the flight has connections
     * @return the total duration of a flight and its connections
     */
    public int getDurationOfTotalTrip() {
        int totalMinutes = 0;
        totalMinutes+= this.getDuration();

        for (Flight connection : this.connections) {
            totalMinutes += connection.getDuration();
        }

        return totalMinutes;
    }

    /**
     * Finds and returns the seats which are available on a flight
     * @return an ArrayList of available Seat objects (not booked)
     */
    public ArrayList<Seat> getAvailableSeats() {
        ArrayList<Seat> availableSeats = new ArrayList<Seat>();
        ArrayList<Seat> allSeats = this.getSeats();

        for (Seat seat : allSeats) {
            if (!seat.getIsBooked()) availableSeats.add(seat);
        }

        return availableSeats;
    }

    /**
     * Sets this flight's flightID
     * @param flightID the flightID to set
     */
    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }

    /**
     * Sets this flight's departureCode
     * @param departureCode the departure code to set
     */
    public void setDepartureCode(String departureCode) {
        this.departureCode = departureCode;
    }

    /**
     * Sets this flight's departureDate
     * @param departureDate the departure date to set
     */
    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    /**
     * Sets this flight's departureTime
     * @param departureTime the departure time to set
     */
    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    /**
     * Sets this flight's arrivalDate
     * @param arrivalDate the arrival date to set
     */
    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    /**
     * Sets this flight's arrival time
     * @param arrivalTime the arrivalTime to set
     */
    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    /**
     * Sets this flight's number of available first class seats
     * @param numAvailFirstSeats the number of seats to set
     */
    public void setNumAvailFirstSeats(int numAvailFirstSeats) {
        this.numAvailFirstSeats = numAvailFirstSeats;
        if (this.numAvailFirstSeats < 1) checkForFull();
    }

    /**
     * Sets this flight's number of available business class seats
     * @param numAvailFirstSeats the number of seats to set
     */
    public void setNumAvailBusinessSeats(int numAvailBusinessSeats) {
        this.numAvailBusinessSeats = numAvailBusinessSeats;
        if (this.numAvailBusinessSeats < 1) checkForFull();
    }

    /**
     * Sets this flight's number of available economy class seats
     * @param numAvailFirstSeats the number of seats to set
     */
    public void setNumAvailEconomySeats(int numAvailEconomySeats) {
        this.numAvailEconomySeats = numAvailEconomySeats;
        if (this.numAvailEconomySeats < 1) checkForFull();
    }

    /**
     * Sets this flight's full status
     * @param isFull the full status to set
     */
    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    /**
     * Sets this flight's international status
     * @param isInternational the international status to set
     */
    public void setIsInternational(boolean isInternational) {
        this.isInternational = isInternational;
    }

    /**
     * Sets this flight's seats
     * @param seats the seats to set
     */
    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    /**
     * Sets this flight's connections
     * @param connections the connections to set
     */
    public void setConnections(ArrayList<Flight> connections) {
        this.connections = connections;
    }

    /**
     * Checks to see if this flight is full
     */
    public void checkForFull() {
        if (this.numAvailFirstSeats < 1 && this.numAvailBusinessSeats < 1 && this.numAvailEconomySeats < 1) this.isFull = true;
        else this.isFull = false;
    }

    /**
     * Removes one first class seat from the current number
     */
    public void removeOneFirstSeat() {
        if (this.numAvailFirstSeats < 1) return;
        this.numAvailFirstSeats--;
    }

    /**
     * Removes one business class seat from the current number
     */
    public void removeOneBusinessSeat() {
        if (this.numAvailBusinessSeats < 1) return;
        this.numAvailBusinessSeats--;
    }

    /**
     * Removes one economy class seat from the current number
     */
    public void removeOneEconomySeat() {
        if (this.numAvailEconomySeats < 1) return;
        this.numAvailEconomySeats--;
    }

    /**
     * Returns this flight's connections
     * @return the connections of this flight
     */
    public ArrayList<Flight> getConnections(){
        return this.connections;
    }

    /**
     * Formats and returns this flight's connections as a readable String
     * @return a list of this flight's connections
     */
    public String getConnectionsList() {
        String list = "";

        if (this.connections.isEmpty()) return "None (Direct Flight)";

        for (Flight connection : this.connections) {
            list += connection.getDepartureCode() + " --> " + connection.getArrivalCode() + "\nDuration: " + connection.getDuration() + "\n";
        }

        return list;
    }

    /**
     * Searches through this flight's seats for one whose seatID matches the one provided
     * @param seatID the seatID of the seat to find and return
     * @return the Seat object whose seatID matches the one provided
     */
    public Seat getSeatByID(int seatID) {
        for (Seat seat : this.seats) {
            if (seat.getSeatID() == (seatID)) return seat;
        }
        return null;
    }

    /**
     * A toString method that converts this object into a human-readable String
     * @return the String which represents this object
     */
    @Override
    public String toString() {
        return this.departureCode + " --> " + this.arrivalCode + "\nDeparting at: " + this.departureTime + 
        "\nArriving at: " + this.arrivalTime + 
        "\nPrice: $" + this.price + 
        "\nDuration: " + this.getDuration() + " minutes" + 
        "\nConnections:\n" + getConnectionsList() + "\n";
    }
}