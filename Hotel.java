import java.util.ArrayList;
import java.util.UUID;

/**
 * Object to define a Hotel with rooms and descriptors
 */
public class Hotel {
    private UUID hotelID;
    private int hotelRating;
    private int capacity;
    private String hotelName;
    private String location;
    private String airportCode;
    private ArrayList<HotelRoom> rooms;

    /**
     * Defines Hotel object
     * @param hotelID
     * @param hotelRating
     * @param capacity
     * @param hotelName
     * @param location
     * @param airportCode
     * @param rooms
     */
    public Hotel(UUID hotelID, int hotelRating, int capacity, String hotelName, String location, String airportCode, ArrayList<HotelRoom> rooms){
        this.hotelID = hotelID;
        this.hotelRating = hotelRating;
        this.capacity = capacity;
        this.hotelName = hotelName;
        this.location = location;
        this.airportCode = airportCode;
        this.rooms = rooms;
    }

    /**
     * 
     * @return UUID of hotelID
     */
    public UUID getHotelID(){
        return this.hotelID;
    }

    /**
     * 
     * @return int of hotel rating
     */
    public int getHotelRating(){
        return this.hotelRating;
    }

    /**
     * 
     * @return int of hotel capacity
     */
    public int getCapacity(){
        return this.capacity;
    }

    /**
     * 
     * @return String of hotel name
     */
    public String getHotelName(){
        return this.hotelName;
    }

    /**
     * 
     * @return String of hotel location
     */
    public String getLocation(){
        return this.location;
    }

    /**
     * 
     * @return String of airport code
     */
    public String getAirportCode(){
        return this.airportCode;
    }

    /**
     * 
     * @return ArrayList of HotelRoom for all rooms in hotel
     */
    public ArrayList<HotelRoom> getRooms(){
        return this.rooms;
    }

    /**
     * 
     * @param roomID
     * @return HotelRoom with given roomID
     */
    public HotelRoom getRoomByID(int roomID) {
        for (HotelRoom room : rooms) {
            if (room.getRoomID() == roomID) return room;
        }
        return null;
    }

    /**
     * 
     * @param bedType
     */
    public void setRoomByBed(HotelRoom bedType){
        
    }

    /**
     * sets UUID of Hotel
     * @param hotelID
     */
    public void setHotelID(UUID hotelID){
        this.hotelID = hotelID;
    }

    /**
     * sets int star rating of hotel
     * @param hotelRating
     */
    public void setHotelRating(int hotelRating){
        this.hotelRating = hotelRating;
    }

    /**
     * sets int of capacity
     * @param capacity
     */
    public void setCapacity(int capacity){
        this.capacity = capacity;
    }

    /**
     * sets String of hotel name
     * @param hotelName
     */
    public void setHotelName(String hotelName){
        this.hotelName = hotelName;
    }

    /**
     * sets String of hotel location
     * @param location
     */
    public void setLocation(String location){
        this.location = location;
    }

    /**
     * sets location defined by airport code
     * @param airCode
     */
    public void setAirportCode(String airCode){
        this.airportCode = airCode;
    }

    /**
     * sets ArrayList of HotelRoom objects to hotel
     * @param rooms
     */
    public void setRooms(ArrayList<HotelRoom> rooms){
        this.rooms = rooms;
    }

    @Override
    public String toString() {
        return this.hotelName + " with a rating of " + this.hotelRating + " located at " + this.location;
    }
}
