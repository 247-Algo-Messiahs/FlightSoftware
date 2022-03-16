import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class Flight {
    private String flightID;
    private String departureCode;
    private String arrivalCode;
    private LocalDate departureDate;
    private LocalTime departureTime;
    private LocalDate arrivalDate;
    private LocalTime arrivaltime;
    private int firstSeats;
    private int businessSeats;
    private int economySeats;
    private boolean isFull;
    private boolean isInternational;
    private ArrayList<Seat> seats;
    
    public Flight(String departureCode, String arrivalCode, LocalDate departureDate, LocalTime departureTime, LocalDate arrivalDate, LocalTime arrivalTime, int firstSeats, int businessSeats, int economySeats, boolean isFull){

    }

    private void setIsFull(int firstSeats, int businessSeats, int economySeats){

    }

    public boolean getIsFull(){
        return false;
    }
}
