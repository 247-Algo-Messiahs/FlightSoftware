import java.util.ArrayList;

public class DataWriter {
    private static DataWriter dataWriter;

    private DataWriter() {

    }

    public void saveUsers(ArrayList<User> users) {

    }
    
    public void saveFlights(ArrayList<Flight> flights) {

    }

    public void saveHotels(ArrayList<Hotel> hotels) {
        
    }

    public static DataWriter getInstance() {
        if (dataWriter == null) dataWriter = new DataWriter();
        return dataWriter;
    }
}
