import java.util.UUID;

public class Hotel {
    private UUID hotelID;
    private int hotelRating;
    private int capacity;
    private String hotelName;
    private String location;
    private String airportCode;
    private HotelRoom[] rooms;

    public Hotel(){

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

    public HotelRoom[] getRooms(){
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

    public void getRooms(HotelRoom[] rooms){
        this.rooms = rooms;
    }

  

}
