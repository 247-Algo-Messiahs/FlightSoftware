import java.util.ArrayList;
import java.util.Date;

public class HotelBooking {
    private RegisteredUser user;
    private HotelRoom hotelRoom;
    private ArrayList<Date> dates;
    private String hotelName;

    public HotelBooking(RegisteredUser user, HotelRoom hotelRoom, ArrayList<Date> dates, String hotelName){

    }

    public RegisteredUser getRegisteredUser(){
        return this.user;
    }

    public HotelRoom getHotelRoom(){
        return this.hotelRoom;
    }

    public ArrayList<Date> getDates(){
        return this.dates;
    }

    public String getHotelName(){
        return this.hotelName;
    }

    public void setRegisteredUser(RegisteredUser user){
        this.user = user;
    }

    public void setHotelRoom(HotelRoom hotelRoom){
        this.hotelRoom = hotelRoom;
    }

    public void getDates(ArrayList<Date> dates){
        this.dates = dates;
    }

    public void getHotelName(String hotelName){
        this.hotelName = hotelName;
    }

    public void confirmBooking(){

    }

    public void displayBooking(){
        
    }
    
}
