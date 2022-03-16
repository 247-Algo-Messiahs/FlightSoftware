public class Seat {

    private SeatType seatType;
    private SeatClass seatClass;
    private String seatNumber;
    private String flightID;
    private boolean isBooked;

    public SeatType getSeatType() {
        return this.seatType;
    }

    public SeatClass getSeatClass() {
        return this.seatClass;
    }

    public String getSeatNumber() {
        return this.seatNumber;
    }

    public String getFlightID() {
        return this.flightID;
    }

    public boolean getIsBooked() {
        return this.isBooked;
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
}
