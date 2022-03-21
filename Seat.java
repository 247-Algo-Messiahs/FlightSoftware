import java.util.UUID;

public class Seat {

    private int seatID;
    private SeatType seatType;
    private SeatClass seatClass;
    private String seatNumber;
    private UUID flightID;
    private boolean isBooked;

    public Seat(int seatID, String seatType, String seatClass, String seatNumber, UUID flightID, boolean isBooked) {
        this.seatID = seatID;
        this.seatType = SeatType.valueOf(seatType);
        this.seatClass = SeatClass.valueOf(seatClass);
        this.seatNumber = seatNumber;
        this.flightID = flightID;
        this.isBooked = isBooked;
    }

    public int getSeatID() {
        return this.seatID;
    }

    public SeatType getSeatType() {
        return this.seatType;
    }

    public SeatClass getSeatClass() {
        return this.seatClass;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public UUID getFlightID() {
        return this.flightID;
    }

    public boolean getIsBooked() {
        return this.isBooked;
    }

    public void setSeatID(int seatID) {
        this.seatID = seatID;
    }

    public void setSeatType(SeatType seatType) {
        this.seatType = seatType;
    }

    public void setSeatClass(SeatClass seatClass) {
        this.seatClass = seatClass;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setIsBooked(boolean isBooked) {
        this.isBooked = isBooked;
    }

    @Override
    public String toString() {
        return this.seatNumber + " with seatID of " + seatID + " in " + seatClass + " class";
    }
}
