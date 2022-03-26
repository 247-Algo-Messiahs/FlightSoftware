import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.time.*;

public class UserInterface {
    private static UserInterface userInterface;
    private Scanner scanner;
    private Facade facade;
    private static final String WELCOME_MESSAGE = "Welcome to Canoe's Flight Booking Software!\n\n Please choose from the following options:";
    private String[] welcomeMenuOptions = {"Log In","Create an Account","Continue as Guest","Search for Flight","Exit"};
    private String[] loginErrorOptions = {"Re-enter Information", "Create an Account", "Continue as Guest"};
    private String[] guestErrorOptions = {"Log in", "Create an Account", "Continue as Guest (you will not be able to create or view bookings"};
    private String[] mainMenuOptions = {"Search for Flight", "Search for Hotel", "View Booking History", "Exit"};
    private String[] nextPageOptions = {"Next Page","Back"};
    private String[] flightFilterOptions = {"Price (Lowest -> Highest)","Duration (Shortest -> Longest", "Departure Time (Earliest -> Latest", "Back"};
    private String[] bookingHistoryptions = {"Main Menu","Quit"};
    private boolean roundTrip;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/uuuu");


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
                        exit();
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
        scanner.nextLine(); //this fixes the problem listed below; it "absorbs" the newline character that is left after the nextInt() above
        System.out.println("Address:");
        String address = scanner.nextLine(); //for some reason it won't allow user to record address
        System.out.println("Phone Number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Email Address:");
        String emailAddress = scanner.nextLine();
        System.out.println("\n");
        System.out.println("Do you have a valid passport? (Y/N)");
        String passportInput = scanner.nextLine();
        boolean passport = passportInput.toLowerCase().equals("y");
        
        facade.createAccount(newUsername, newPassword, firstName, lastName, age, address, phoneNumber, emailAddress, passport);
        mainMenu();
        

        //gonna probably need a function somehwere that converts newUsername to username

    }


    private void searchForFlight(){
        printHeading(" Flight Search ");
        System.out.println("Roundtrip: (Y/N)");
        //next 2 lines record Y/N from scanner.nextLine
        String roundtripInput = scanner.nextLine();
        boolean roundTrip = roundtripInput.toLowerCase().equals("y");
        System.out.println("Departing Airport Code:");
        String departingCode = scanner.nextLine();
        System.out.println("Arrival Airport Code:");
        String arrivalCode = scanner.nextLine();
        System.out.println("Number of Passengers:");
        int passengers = scanner.nextInt();
        scanner.nextLine();

        ArrayList<User> guests = new ArrayList<User>();

        if (passengers > 1) guests = enterGuestInfo(passengers - 1);

        facade.searchForFlight(roundTrip, departingCode, arrivalCode, passengers);

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
                    if(roundTrip = true){
                        roundtripFlightSearch();
                        break;
                    }
                    else{
                        onewayFlightSearch();
                        break;
                    }
                            
                case(1):
                        searchForFlight();
                        break;
            }
        }   
    }
    private void roundtripFlightSearch(){
        printHeading(" Flight Search ");
        System.out.println("Departure Date: (mm/dd/yyyy)");
        LocalDate departureDate = LocalDate.parse(scanner.nextLine(), FORMATTER);
        System.out.println("Return Date: (mm/dd/yyyy)");
        LocalDate arrivalDate = LocalDate.parse(scanner.nextLine(), FORMATTER);
    }
    
    private void onewayFlightSearch(){
        printHeading(" Flight Search ");
        System.out.println("Departure Date: (mm/dd/yyyy)");
        LocalDate departureDate = LocalDate.parse(scanner.nextLine(), FORMATTER);

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
                
                   flightFilter();
                    break;
         
                case(1):
                    searchForFlight();
                        break;
            }
        }   
    }
    
    private void flightFilter(){
        printHeading(" Flight Filter ");
        System.out.println("How would you like your search results to be sorted?");
        

        for(int i=0;i<flightFilterOptions.length;i++){
            System.out.println((i+1) + ". " + flightFilterOptions[i]);
        }
        while(true){
            int userCommand = getUserSelection(flightFilterOptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
            }

            if(userCommand == flightFilterOptions.length -1) break;

            switch(userCommand) {
                case(0):
                    //sort price
                    departingFlightResults();
                    break;
         
                case(1):
                    //sort duration
                    departingFlightResults();
                    break;
                case(2):
                    departingFlightResults();
                    break;
                case(3):
                if(roundTrip = true){
                    roundtripFlightSearch();
                    break;
                }
                else{
                    onewayFlightSearch();
                    break;
                }
            }
        }   
    }

    private void departingFlightResults(){
        printHeading(" Departing Flight: ");

        //SHOW DEPARTING FLIGHT SEARCH RESULTS HERE
    }

    private void searchForHotel(){
        printHeading(" Hotel Search ");
        System.out.println("Please input your desired locations, check-in, and check-out dates below.");
        System.out.println("\n");
        System.out.println("Nearby Airport Code");
        String nearbyAirportCode = scanner.nextLine();      //would the variable be this or something different because the only other variables are "departingCode" and "arrivalCode"
        System.out.println("Check-in Date: (mm/dd/yyyy");

        
        LocalDate checkinDate = LocalDate.parse(scanner.nextLine(), FORMATTER);     //how do you read in LocalDate scanner
        System.out.println("Check-out Date: (mm/dd/yyyy");
        LocalDate checkoutDate = LocalDate.parse(scanner.nextLine(), FORMATTER);     //how do you read in LocalDate scanner
        hotelSearchResults();
    }

    private void hotelSearchResults(){
        
    }
    private void viewBookingHistory(){
        printHeading(" Booking History ");
        System.out.println("You may view your booking history below or choose from one of the following options.\n");

        ArrayList<FlightBooking> bookings = facade.getBookingHistory();

        for (FlightBooking booking : bookings) {
            System.out.println(booking);
        }

        for(int i=0;i<bookingHistoryptions.length;i++){
            System.out.println((i+1) + ". " + bookingHistoryptions[i]);
        }
        while(true){
            int userCommand = getUserSelection(bookingHistoryptions.length);
			
			if(userCommand == -1) {
				System.out.println("Not a valid command");
				continue;
            }

            if(userCommand == bookingHistoryptions.length -1) exit();

            switch(userCommand) {
                case(0):
                
                   mainMenu();
                    break;
         
                case(1):
                    exit();
                    break;
            }
        }   

                //SHOW BOOKING HISTORY HERE

    }
    
    private void exit(){
        printHeading(" Canoe Booking ");
        System.out.println("Thank you for chooisng Canoe Booking! Happy travels!");
        System.exit(0);
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

    public ArrayList<User> enterGuestInfo(int numGuests) {
        printHeading(" Guest Info ");

        System.out.println("You indicated you have additional passengers. Please enter the information for your passengers.");

        ArrayList<User> guests = new ArrayList<User>();

        for (int i = 0; i < numGuests; i++) {

            System.out.println("Guest " + (i + 1) + "First Name:");
            String firstName = scanner.nextLine();
            System.out.println("Guest " + (i + 1) + "Last Name:");
            String lastName = scanner.nextLine();
            System.out.println("Guest " + (i + 1) + "Age:");
            int age = scanner.nextInt();
            scanner.nextLine();

            guests.add(new User(age, firstName, lastName));
        }

        return guests;
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
                        exit();
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