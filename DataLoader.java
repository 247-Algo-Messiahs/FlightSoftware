import java.util.ArrayList;

public class DataLoader {
    private static DataLoader dataLoader;

    private DataLoader() {
        
    }
    
    public ArrayList<User> getAllUsers() {
        return null;
    }

    public ArrayList<Flight> getAllFlights() {
        return null;
    }

    public ArrayList<Hotel> getAllHotels() {
        return null;
    }

    public static DataLoader getInstance() {
        if (dataLoader == null) dataLoader = new DataLoader();
        return dataLoader;
    }
}
