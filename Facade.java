public class Facade {
    private static Facade facade;
    private UserList userList;
    private FlightList flightList;
    private HotelList hotelList;
    private DataLoader dataLoader;
    private DataWriter dataWriter;


    private Facade() {
        this.userList = userList.getInstance();
        this.flightList = flightList.getInstance();
        this.hotelList = hotelList.getInstance();
        this.dataLoader = dataLoader.getInstance();
        this.dataWriter = dataWriter.getInstance();
    }

    public Facade getInstance() {
        if (this.facade == null) this.facade = new Facade();
        return this.facade;
    }
    
}