import java.util.ArrayList;

public class Preferences {
    private SeatType seatType;
    private SeatClass seatClass;
    private RoomTrait hotelRoom;
    private String airportCode;

    public Preferences(String seatType, String seatClass, String hotelRoom, String airportCode) {
        this.seatType = SeatType.valueOf(seatType);
        this.seatClass = SeatClass.valueOf(seatClass);
        this.hotelRoom = RoomTrait.valueOf(hotelRoom);
        this.airportCode = airportCode;
    }
}
