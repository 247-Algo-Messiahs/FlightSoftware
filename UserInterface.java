import java.util.Scanner;
import java.util.UUID;

public class UserInterface {
    private static UserInterface userInterface;
    private Scanner scanner;
    private Facade facade;
    
    private UserInterface() {
        this.scanner = new Scanner(System.in);
        this.facade = Facade.getInstance();
    }

    public static UserInterface getInstance() {
        if (userInterface == null) userInterface = new UserInterface();
        return userInterface;
    }

    public void run() {

        RegisteredUser testUser = UserList.getRegisteredUserByUUID(UUID.fromString("74432d6e-394c-4ea7-bb77-ed3c53ac5226"));
        Hotel testHotel = HotelList.getHotelByUUID((UUID.fromString("c6a8b332-2d21-4fbe-b793-e503cbd8e1d4")));

        System.out.println(UserList.getRegisteredUserByUUID(UUID.fromString("74432d6e-394c-4ea7-bb77-ed3c53ac5226")));
        System.out.println(FlightList.getFlightByUUID(UUID.fromString("0c9e5d25-877d-4171-be90-627827007a7a")));
        System.out.println(FlightList.getFlightByUUID(UUID.fromString("0c9e5d25-877d-4171-be90-627827007a7a")).getSeats().get(1));
        
        facade.loadUserFlightBookings(testUser);
        System.out.println(facade.getUserFlightBookings(testUser));

        facade.loadUserHotelBookings(testUser);
        System.out.println(facade.getUserHotelBookings(testUser));

        System.out.println(testHotel);

        System.out.println(testHotel.getRooms().get(0));
    }   

    private int getUserSelection(int numCommands) {
        String input = scanner.nextLine();
        int command = Integer.parseInt(input);

        if (command < 1 || command > numCommands) return -1;
        else return command;

    }

    public void printHeading(String heading) {
        System.out.println("**********" + heading + "**********");
    }
    public static void main(String[] args) {
        UserInterface UI = new UserInterface();
        UI.run();
    }
}