import java.util.ArrayList;
import java.io.FileWriter;
import java.io.PrintWriter;
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

    public void printBooking() {
        PrintWriter out;
        try {
            out = new PrintWriter(new FileWriter("bookings.txt", true)); //Opens file

            Hotel hotel = HotelList.getHotelByUUID(this.hotelID);

            out.println(this.toString() + "\n");

            out.close(); //close the file
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String toString(){
        Hotel hotel = HotelList.getHotelByUUID(this.hotelID);
        HotelRoom hotelRoom = hotel.getRoomByID(this.roomID);

        return "Hotel Booking" + 
        "\nHotel ID: " + this.hotelID + 
        "\nRoom ID: " + this.roomID + 
        "\nAddress: "+ hotel.getLocation() +
        "\nRoom Size: " + hotelRoom.getBedType().toString().toLowerCase() +
        "\nDates of Stay: \n" + getBookedDatesList();
    }

    
    
}
