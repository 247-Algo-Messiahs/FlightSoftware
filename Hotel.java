import java.util.ArrayList;
import java.util.UUID;

public class Hotel {
    private UUID hotelID;
    private int hotelRating;
    private int capacity;
    private String hotelName;
    private String location;
    private String airportCode;
    private ArrayList<HotelRoom> rooms;

    public Hotel(UUID hotelID, int hotelRating, int capacity, String hotelName, String location, String airportCode, ArrayList<HotelRoom> rooms){
        this.hotelID = hotelID;
        this.hotelRating = hotelRating;
        this.capacity = capacity;
        this.hotelName = hotelName;
        this.location = location;
        this.airportCode = airportCode;
    }

    public UUID getHotelID(){
        return this.hotelID;
    }

    public int getHotelRating(){
        return this.hotelRating;
    }

    public int getCapacity(){
        return this.capacity;
    }

    public String getHotelName(){
        return this.hotelName;
    }

    public String getLocation(){
        return this.location;
    }

    public String getAirCode(){
        return this.airportCode;
    }

    public ArrayList<HotelRoom> getRooms(){
        return this.rooms;
    }


    public void setHotelID(UUID hotelID){
        this.hotelID = hotelID;
    }

    public void setHotelRating(int hotelRating){
        this.hotelRating = hotelRating;
    }

    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    public void setHotelName(String hotelName){
        this.hotelName = hotelName;
    }

    public void setLocation(String location){
        this.location = location;
    }

    public void setAirportCode(String airCode){
        this.airportCode = airCode;
    }

    public void setRooms(ArrayList<HotelRoom> rooms){
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return this.hotelName + " with a rating of " + this.hotelRating + " located at " + this.location;
    }
}
