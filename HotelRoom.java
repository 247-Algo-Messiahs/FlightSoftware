import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class HotelRoom {
    private ArrayList<RoomTrait> roomType;
    private UUID hotelID;
    private ArrayList<Date> notAvail;
    
    public HotelRoom(ArrayList<RoomTrait> roomType, UUID hotelID, ArrayList<Date> notavail){

    }

    public ArrayList<RoomTrait> getRoomType(){
        return this.roomType;
    }

    public UUID getHotelID(){
        return this.hotelID;
    }

    public ArrayList<Date> getNotAvail(){
        return this.notAvail;
    }

    public void setRoomType(ArrayList<RoomTrait> roomType){
        this.roomType = roomType;
    }

    public void setHotelID(UUID hotelID){
        this.hotelID = hotelID;
    }

    public void setNotAvail(ArrayList<Date> notAvail){
        this.notAvail = notAvail;
    }

    

    public boolean isSmoking(){
        return false;
    }

    public RoomTrait getBedType(){
        return null;
    }

    public boolean hasBalcony(){
        return false;
    }

    public boolean hasPulloutCouch(){
        return false;
    }
}
