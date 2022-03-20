import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;

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
    
    public Flight(UUID flightID, String departureCode, String arrivalCode, LocalTime departureTime, LocalTime arrivalTime, int numAvailFirstSeats,
     int numAvailBusinessSeats, int numAvailEconomySeats, boolean isFull, boolean isInternational) {
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
    }

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

    public boolean getIsFull() {
        return this.isFull;
    }

    public UUID getFlightID() {
        return this.flightID;
    }

    public String getDepartureCode() {
        return this.departureCode;
    }

    public String getArrivalCode() {
        return this.arrivalCode;
    }

    public LocalDate getDepartureDate() {
        return this.departureDate;
    }

    public LocalTime getDepartureTime() {
        return this.departureTime;
    }

    public LocalDate getArrivalDate() {
        return this.arrivalDate;
    }

    public LocalTime getArrivalTime() {
        return this.arrivalTime;
    }

    public int getNumAvailFirstSeats() {
        return this.numAvailFirstSeats;
    }

    public int getNumBusinessSeats() {
        return this.numAvailBusinessSeats;
    }

    public int getNumEconomySeats() {
        return this.numAvailEconomySeats;
    }

    public boolean getIsInternational() {
        return this.isInternational;
    }

    public ArrayList<Seat> getSeats() {
        return this.seats;
    }

    public void setFlightID(UUID flightID) {
        this.flightID = flightID;
    }

    public void setDepartureCode(String departureCode) {
        this.departureCode = departureCode;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public void setDepartureTime(LocalTime departureTime) {
        this.departureTime = departureTime;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setNumAvailFirstSeats(int numAvailFirstSeats) {
        this.numAvailFirstSeats = numAvailFirstSeats;
        if (this.numAvailFirstSeats < 1) checkForFull();
    }

    public void setNumAvailBusinessSeats(int numAvailBusinessSeats) {
        this.numAvailBusinessSeats = numAvailBusinessSeats;
        if (this.numAvailBusinessSeats < 1) checkForFull();
    }

    public void setNumAvailEconomySeats(int numAvailEconomySeats) {
        this.numAvailEconomySeats = numAvailEconomySeats;
        if (this.numAvailEconomySeats < 1) checkForFull();
    }

    public void setIsFull(boolean isFull) {
        this.isFull = isFull;
    }

    public void setIsInternational(boolean isInternational) {
        this.isInternational = isInternational;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void checkForFull() {
        if (this.numAvailFirstSeats < 1 && this.numAvailBusinessSeats < 1 && this.numAvailEconomySeats < 1) this.isFull = true;
        else this.isFull = false;
    }

    public void removeOneFirstSeat() {
        if (this.numAvailFirstSeats < 1) return;
        this.numAvailFirstSeats--;
    }

    public void removeOneBusinessSeat() {
        if (this.numAvailBusinessSeats < 1) return;
        this.numAvailBusinessSeats--;
    }

    public void removeOneEconomySeat() {
        if (this.numAvailEconomySeats < 1) return;
        this.numAvailEconomySeats--;
    }

    @Override
    public String toString() {
        return this.departureCode + " --> " + this.arrivalCode + "\nDeparting at: " + this.departureTime + "\nArriving at: " + this.arrivalTime;
    }
}