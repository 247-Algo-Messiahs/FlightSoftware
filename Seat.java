import java.util.UUID;

/**
 * @author Nicolas Becker, Peyton Tucker, Avery Slomnicki, Austin Hanson
 */
public class Seat {

    private int seatID;
    private SeatType seatType;
    private SeatClass seatClass;
    private String seatNumber;
    private UUID flightID;
    private boolean isBooked;

    /**
     * Defines given seat object
     * @param seatID
     * @param seatType
     * @param seatClass
     * @param seatNumber
     * @param flightID
     * @param isBooked
     */
    public Seat(int seatID, String seatType, String seatClass, String seatNumber, UUID flightID, boolean isBooked) {
        this.seatID = seatID;
        this.seatType = SeatType.valueOf(seatType);
        this.seatClass = SeatClass.valueOf(seatClass);
        this.seatNumber = seatNumber;
        this.flightID = flightID;
        this.isBooked = isBooked;
    }

    /**
     * @return int of given seatID
     */
    public int getSeatID() {
        return this.seatID;
    }

    /**
     * 
     * @return SeatType
     */
    public SeatType getSeatType() {
        return this.seatType;
    }

    /**
     * @return SeatClass
     */
    public SeatClass getSeatClass() {
        return this.seatClass;
    }

    /**
     * 
     * @return String of seatNumber
     */
    public String getSeatNumber() {
        return this.seatNumber;
    }

    /**
     * 
     * @return UUID of flightID
     */
    public UUID getFlightID() {
        return this.flightID;
    }

    /**
     * 
     * @return boolean of a seat's booked status
     */
    public boolean getIsBooked() {
        return this.isBooked;
    }

    /**
     * Sets given seat's ID
     * @param seatID
     */
    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    /**
     * Sets given seat's SeatType
     * @param seatType
     */
    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    /**
     * Sets given seat's SeatClass
     * @param seatClass
     */
    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    /**
     * Sets given seat number via String
     * @param seatNumber
     */
    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    /**
     * Sets given seat's booked status
     * @param isBooked
     */
    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    @Override
    public String toString() {
        return this.seatNumber + " with seatID of " + seatID + " in " + seatClass + " class. Booked: " + this.isBooked ;
    }
}
