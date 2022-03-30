import java.util.ArrayList;
import java.time.LocalDate;
import java.util.UUID;

public class HotelBooking {
    private UUID hotelID;
    private int roomID;
    private ArrayList<LocalDate> dates;

    public HotelBooking(UUID hotelID, int roomID, ArrayList<LocalDate> dates){
        this.hotelID = hotelID;
        this.roomID = roomID;
        this.dates = new ArrayList<LocalDate>(dates);
    }

    public UUID getHotelID(){
        return this.hotelID;
    }
    
    public int getRoomID(){
        return this.roomID;
    }

    public ArrayList<LocalDate> getDates(){
        return this.dates;
    }

    public void setDates(ArrayList<LocalDate> dates){
        this.dates = dates;
    }

    public void confirmBooking(){

    }

    private String getBookedDatesList() {
        String list = "";
        
        for (int i = 0; i < this.dates.size(); i++) {
            list += i+1 + ") " + dates.get(i) + "\n";
        }

        return list;
    }

    public String toString(){
        Hotel hotel = HotelList.getHotelByUUID(this.hotelID);
        HotelRoom hotelRoom = hotel.getRoomByID(this.roomID);

        return "Hotel Booking:" + 
        "\nHotel ID: " + this.hotelID + 
        "\nRoom ID: " + this.roomID + 
        "\nRoom Size: " + hotelRoom.getBedType().toString().toLowerCase() +
        "\nDates of Stay: \n" + getBookedDatesList();
    }

    
    
}
