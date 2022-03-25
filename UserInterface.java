import java.util.Scanner;
import java.util.UUID;

public class UserInterface {
    private static UserInterface userInterface;
    private Scanner scanner;
    private Facade facade;
    private static final String WELCOME_MESSAGE = "Welcome to Canoe’s Flight Booking Software!\n\n Please choose from the following options:";
    private String[] welcomeMenuOptions = {"Log In","Create an Account","Continue as Guest","Search for Flight","Exit"};
    private String[] loginErrorOptions = {"Re-enter Information", "Create an Account", "Continue as Guest"};
    private String[] guestErrorOptions = {"Log in", "Create an Account", "Continue as Guest (you will not be able to create or view bookings"};
    private String[] mainMenuOptions = {"Search for Flight", "Search for Hotel", "View Booking History", "Quit"};
    private String[] nextPageOptions = {"Next Page","Back"};

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
            for(int i=0;i<welcomeMenuOptions.length;i++){
                System.out.println((i+1) + ". " + welcomeMenuOptions[i]);
            }
            System.out.println("\n");

            int userCommand = getUserSelection(welcomeMenuOptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
            }

            if(userCommand == welcomeMenuOptions.length -1) break;

            switch(userCommand) {
                case(0):
                        logIn();
                        break;
                case(1):
                        createAccount();
                        break;
                case(2):
                        mainMenu();
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


    
    //scanner.nextInt();
    //scanner.nextLine();
    

    private int getUserSelection(int numCommands) {
        String input = scanner.nextLine();
        int command = Integer.parseInt(input) - 1;

        if(command >=0 && command <= numCommands -1) return command;

        return -1;






        //int command = Integer.parseInt(input);

        //if (command < 1 || command > numCommands) return -1;
       // else return command;

    }

    private void logIn(){
        printHeading(" Log In ");
        System.out.println("Please enter your login information.");
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();
        facade.logIn(username, password);

        

        //Need to determine if the login is valid or not. Then call "mainMenu()"
        
    }

    private void createAccount(){
        printHeading(" Account Creation ");
        System.out.println("Please enter your information to create an account");
        System.out.println("New Username:");
        String newUsername = scanner.nextLine();
        System.out.println("New Password:");
        String newPassword = scanner.nextLine();
        System.out.println("\n");
        System.out.println("First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Age:");
        int age = scanner.nextInt();
        System.out.println("Address:");
        String address = scanner.nextLine(); //for some reason it won't allow user to record address
        System.out.println("Phone Number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Email Address:");
        String emailAddress = scanner.nextLine();
        System.out.println("\n");
        System.out.println("Do you have a valid passport? (Y/N)");
        boolean passport = scanner.nextBoolean();
        facade.createAccount(newUsername, newPassword, firstName, lastName, age, address, phoneNumber, emailAddress, passport );
        mainMenu();
        //how do we store all of this information
        //how do you record the "Y/N" from the boolean
        //gonna probably need a function somehwere that converts newUsername to username

    }


    private void searchForFlight(){
        printHeading(" Flight Search ");
        System.out.println("Roundtrip: (Y/N)"); //        //how do you record the "Y/N" from the boolean
        boolean roundTrip = scanner.nextBoolean();
        System.out.println("Departing Airport Code:");
        String departingCode = scanner.nextLine();
        System.out.println("Arrival Airport Code:");
        String arrivalCode = scanner.nextLine();
        System.out.println("Number of Passengers:");
        int passengers = scanner.nextInt();
        System.out.println("Number of Carry-on Bags:");
        int carryOn = scanner.nextInt();
        System.out.println("Number of Checked Bags:");
        int checked= scanner.nextInt();
        facade.searchForFlight(roundTrip, departingCode, arrivalCode, passengers, carryOn, checked);

        for(int i=0;i<nextPageOptions.length;i++){
            System.out.println((i+1) + ". " + nextPageOptions[i]);
        }
        while(true){
            int userCommand = getUserSelection(nextPageOptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
            }

            if(userCommand == nextPageOptions.length -1) break;

            switch(userCommand) {
                case(0):
                        roundtripFlightSearch();
                        break;
                case(1):
                        searchForFlight();
                        break;
            }
        }   
    }
    private void roundtripFlightSearch(){

    }

    private void searchForHotel(){

    }
    private void viewBookingHistory(){

    }
    private void logOut(){

    }
    private void quit(){

    }

    

    private void loginError(){
        printHeading(" LogIn Error ");
        System.out.println("Your information does not match any user accounts.");
        System.out.println("\n");
        System.out.println("Please choose from the following options:");
        for(int i=0;i<loginErrorOptions.length;i++){
            System.out.println((i+1) + ". " + loginErrorOptions[i]);
        }
        while(true){
            int userCommand = getUserSelection(loginErrorOptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
            }

            if(userCommand == loginErrorOptions.length -1) break;

            switch(userCommand) {
                case(0):
                        loginError();
                        break;
                case(1):
                        createAccount();
                        break;
                case(2):
                        mainMenu();
                        break;
              }
        }
         facade.loginError(); //Don't know why this is never used locally. I created a facade method. 
    }

    private void guestError(){
        printHeading(" Guest Error ");
        System.out.println("You are currently using a guest account and therefore don't have access to this feature.");
        System.out.println("\n");
        System.out.println("Please choose from the following options:");
        for(int i=0;i<guestErrorOptions.length;i++){
            System.out.println((i+1) + ". " + guestErrorOptions[i]);
        }
        while(true){
            int userCommand = getUserSelection(guestErrorOptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
            }

            if(userCommand == guestErrorOptions.length -1) break;

            switch(userCommand) {
                case(0):
                        logIn();
                        break;
                case(1):
                        createAccount();
                        break;
                case(2):
                        mainMenu();
                        break;
              }
        }
         facade.guestError(); //Don't know why this is never used locally. I created a facade method. 
    }
    private void mainMenu(){
        printHeading(" Main Menu ");
        System.out.println("\n");
        System.out.println("Please choose from the following options:");
        for(int i=0;i<mainMenuOptions.length;i++){
            System.out.println((i+1) + ". " + mainMenuOptions[i]);
        }
        while(true){
            int userCommand = getUserSelection(mainMenuOptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
            }

            if(userCommand == mainMenuOptions.length -1) break;

            switch(userCommand) {
                case(0):
                        searchForFlight();
                        break;
                case(1):
                        searchForHotel();
                        break;
                case(2):
                        viewBookingHistory();
                        break;
                case(3):
                        quit();
                        break;
              }
        }
         facade.mainMenu(); 
    }
    
   
    public static void main(String[] args) {
        UserInterface UI = new UserInterface();
        UI.run();
    }
}