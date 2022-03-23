import java.util.Scanner;
import java.util.UUID;

public class UserInterface {
    private UserInterface userInterface;
    private Scanner scanner;
    private Facade facade;
    
    private UserInterface() {
        this.scanner = new Scanner(System.in);
        this.facade = Facade.getInstance();
    }

    public UserInterface getInstance() {
        if (this.userInterface == null) this.userInterface = new UserInterface();
        return this.userInterface;
    }

    public void run() {
        facade.loadAllUsers();
        facade.loadAllFlights();

        RegisteredUser test = facade.getRegisteredUserByUUID(UUID.fromString("74432d6e-394c-4ea7-bb77-ed3c53ac5226"));
        System.out.println(facade.getRegisteredUserByUUID(UUID.fromString("74432d6e-394c-4ea7-bb77-ed3c53ac5226")));
        System.out.println(facade.getFlightByUUID(UUID.fromString("0c9e5d25-877d-4171-be90-627827007a7a")));
        System.out.println(facade.getFlightByUUID(UUID.fromString("0c9e5d25-877d-4171-be90-627827007a7a")).getSeats().get(1));
        System.out.println(facade.getUserFlightBookings(test));
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