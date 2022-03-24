import java.util.Scanner;
import java.util.UUID;

public class UserInterface {
    private static UserInterface userInterface;
    private Scanner scanner;
    private Facade facade;
    private static final String WELCOME_MESSAGE = "Welcome to Canoe’s Flight Booking Software!";
    private String[] mainMenuOptions = {"Log In","Create an Account","Continue as Guest","Search for Flight","Exit"};
    
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


        //TERMINAL STARTS HERE
        System.out.println(WELCOME_MESSAGE);

        while(true){
            displayMainMenu();

            int userCommand = getUserSelection(mainMenuOptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
            }

            if(userCommand == mainMenuOptions.length -1) break;

            switch(userCommand) {
                case(0):
                        logIn();
                        break;
                case(1):
                        createAccount();
                        break;
                case(2):
                        continueAsGuest();
                        break;
                case(3):
                        searchForFlight();
                        break;
                case(4):
                        logOut();
                        break;
            }
        }

        System.out.println("Thank you for choosing Canoe Booking! Happy Travels!");

    }   
    public void printHeading(String heading) {
        System.out.println("**********" + heading + "**********");
    }

    private void displayMainMenu(){
        printHeading(" Main Menu ");
        for(int i=0;i<mainMenuOptions.length;i++){
            System.out.println((i+1) + ". " + mainMenuOptions[i]);
        }
    System.out.println("\n");
    scanner.nextInt();
    scanner.nextLine();
    }

    private int getUserSelection(int numCommands) {
        String input = scanner.nextLine();
        int command = Integer.parseInt(input);

        if (command < 1 || command > numCommands) return -1;
        else return command;

    }

    private void logIn(){
        printHeading(" Log In ");
        System.out.println("Please enter your login information:");
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        facade.logIn(username, password);
    }
    private void createAccount(){

    }
    private void continueAsGuest(){

    }
    private void searchForFlight(){

    }
    private void logOut(){

    }

   
    public static void main(String[] args) {
        UserInterface UI = new UserInterface();
        UI.run();
    }
}