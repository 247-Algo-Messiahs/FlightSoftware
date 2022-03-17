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

    public DataWriter getInstance() {
        if (this.dataWriter == null) this.dataWriter = new DataWriter();
        return this.dataWriter;
    }
}
