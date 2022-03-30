import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;
import java.io.IOException;
import java.time.*;

/**
 * @author Peyton Tucker, Avery Slomnicki
 */
public class UserInterface {
    private static UserInterface userInterface;
    private Scanner scanner;
    private Facade facade;


 /**
* All of the menu options stored in the individual String[]'s that the user can choose from when navigating the system.
 */
    private static final String WELCOME_MESSAGE = "Welcome to Canoe's Flight Booking Software!\n\n Please choose from the following options:";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/uuuu");

    private String[] welcomeMenuOptions = {"Log In","Create an Account","Continue as Guest","Search for Flight","Search for Hotel","Exit"};
    private String[] guestErrorOptions = {"Log in", "Create an Account", "Exit"};
    private String[] mainMenuOptions = {"Search for Flight", "Search for Hotel", "View Booking History", "Exit"};
    private String[] nextPageOptions = {"Next Page","Back"};
    private String[] flightFilterOptions = {"Price (Lowest -> Highest)","Duration (Shortest -> Longest)", "Departure Time (Earliest -> Latest)", "Back"};
    private String[] bookingHistoryptions = {"Main Menu","Quit"};
    private String[] hotelResultsOptions = {"Hotel 1","Hotel 2","Hotel 3", "Main Menu (No Selection)"};
    private String[] roomSizeOptions = {"King","Queen","Double"};
    private String[] checkoutOptions = {"Purchase ","Start a New Search"};
    private String[] completedHotelBookingOptions = {"Main Menu","View Booking History","Exit"};
    private String[] completedFlightBookingOptions = {"Main Menu","View Booking History","Search for Hotel","Exit"};
    private String[] chooseFlightOptions = {"Choose Flight 1","Choose Flight 2","Choose Flight 3","Filter Flights","Return to Flight Search"};

 /**
 * Declaration of Variables
 */
    private boolean roundTrip;
    private boolean chosenReturnFlight;
    private boolean chosenDepartureFlight;
    private boolean redirectGuestToFlightCheckout;
    private boolean redirectGuestToHotelCheckout;
    private String departingCode;
    private String arrivalCode;
    private ArrayList<Guest> guests;
    private int checkedBags;
    private LocalDate departureDate;
    private LocalDate returnDate;
    private FlightTrait flightTrait;
    private BedType bedType;
    private String hotelCode;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private Hotel chosenHotel;
    private HotelRoom chosenHotelRoom;
    private int hotelRoomID;
    private ArrayList<Flight> unfilteredFlights;
    private ArrayList<FlightBooking> flightBookingsInCart;

/**
 * Constructor for the User interface. Reads in scanner/facade and allows to choose departure/return flihts and store them in cart. 
 */
    private UserInterface() {
        this.scanner = new Scanner(System.in);
        this.facade = Facade.getInstance();
        this.flightBookingsInCart = new ArrayList<FlightBooking>();
        chosenReturnFlight = false;
        chosenDepartureFlight = false;
    }
/**
 * Allows the ability to get objects in the class
 * @return
 */
    public static UserInterface getInstance() {
        if (userInterface == null)
            userInterface = new UserInterface();
        return userInterface;
    }

    public void run() {

        /*
         * RegisteredUser testUser = UserList.getRegisteredUserByUUID(UUID.fromString(
         * "74432d6e-394c-4ea7-bb77-ed3c53ac5226"));
         * facade.loadUserFlightBookings(testUser);
         * facade.loadUserHotelBookings(testUser);
         * 
         * /*testing datawriter
         * UUID testId = new UUID(1, 3);
         * RegisteredUser testWriter = new RegisteredUser(testId, "Johnny", "Test",
         * "Fake Ave", "867-5309", "johhnyRox", "Password123", true, 21,
         * "testing@email.com");
         * UserList.addUserToList(testWriter);
         * 
         * 
         * 
         * Hotel testHotel = HotelList.getHotelByUUID((UUID.fromString(
         * "c6a8b332-2d21-4fbe-b793-e503cbd8e1d4")));
         * 
         * System.out.println(UserList.getRegisteredUserByUUID(UUID.fromString(
         * "74432d6e-394c-4ea7-bb77-ed3c53ac5226")));
         * System.out.println(FlightList.getFlightByUUID(UUID.fromString(
         * "00b8ec49-0018-481a-864a-0ea05b6bd7e4")));
         * System.out.println(FlightList.getFlightByUUID(UUID.fromString(
         * "00b8ec49-0018-481a-864a-0ea05b6bd7e4")).getSeats().get(0));
         * 
         * System.out.println(facade.getUserFlightBookings(testUser));
         * 
         * 
         * System.out.println(facade.getUserHotelBookings(testUser));
         * 
         * System.out.println(testHotel);
         * 
         * System.out.println(testHotel.getRooms().get(0));
         *

        // TERMINAL STARTS HERE

        System.out.println(testHotel.getRooms().get(0)); 
        */

        //TERMINAL STARTS HERE
/**
*  Terminal starts here with Welcome Message and Welcome page menu options. 
*/      
        System.out.println(WELCOME_MESSAGE);

        while (true) {
            for (int i = 0; i < welcomeMenuOptions.length; i++) {
                System.out.println((i + 1) + ". " + welcomeMenuOptions[i]);
            }

            int userCommand = getUserSelection(welcomeMenuOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    logIn();
                    break;
                case (1):
                    createAccount();
                    break;
                case (2):
                    mainMenu();
                    break;
                case (3):
                    searchForFlight();
                    break;
                case (4):
                    searchForHotel();
                    break;
                case (5):
                    exit();
                    break;
            }
        }

    } 
 /**
* Call this method when wanting to print a new heading for different sections of the system
* @param heading
*/  
    public void printHeading(String heading) {
        System.out.println("\n\n**********" + heading + "**********\n");
    }

/**
* allows the ability to record which menu option the user chooses
* @param numCommands
* @return
*/
    private int getUserSelection(int numCommands) {
        String input = scanner.nextLine();
        int command = Integer.parseInt(input) - 1;

        if (command >= 0 && command <= numCommands - 1)
            return command;

        return -1;

    }

/**
* User has the ability to LogIn
*/
    private void logIn(){
        printHeading(" Log In ");
        System.out.println("Please enter your login information.");
        System.out.println("Username:");
        String username = scanner.nextLine();
        System.out.println("Password:");
        String password = scanner.nextLine();

        while (!facade.logIn(username, password)) {
            System.out.println("Sorry, your records were not found. Enter 1 to try again, or 2 to create an account.");
            int input = scanner.nextInt();
            scanner.nextLine();

            switch (input) {
                case (1):
                    System.out.println("Please enter your login information.");
                    System.out.println("Username:");
                    username = scanner.nextLine();
                    System.out.println("Password:");
                    password = scanner.nextLine();
                    continue;
                case (2):
                    createAccount();
                    break;
                default:
                    System.out.println("Your entry is not a valid option.");
                    continue;
            }
        }
        System.out.println("Now logged in as: " + facade.getCurrentUser().getUsername());

        if (redirectGuestToFlightCheckout) flightCheckout();
        if (redirectGuestToHotelCheckout) hotelCheckout();

        mainMenu();
    }

/**
* User has the ability to create a new account
*/
    private void createAccount(){
        printHeading(" Account Creation ");
        System.out.println("Please enter your information to create an account");
        System.out.println("New Username:");
        String newUsername = scanner.nextLine();
        System.out.println("New Password:");
        String newPassword = scanner.nextLine();
        System.out.println("First Name:");
        String firstName = scanner.nextLine();
        System.out.println("Last Name:");
        String lastName = scanner.nextLine();
        System.out.println("Age:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Address:");
        String address = scanner.nextLine();
        System.out.println("Phone Number:");
        String phoneNumber = scanner.nextLine();
        System.out.println("Email Address:");
        String emailAddress = scanner.nextLine();
        System.out.println("Do you have a valid passport? (Y/N)");
        String passportInput = scanner.nextLine();
        boolean passport = passportInput.toLowerCase().equals("y");

        if (redirectGuestToFlightCheckout) flightCheckout();
        if (redirectGuestToHotelCheckout) hotelCheckout();

        mainMenu();

        // gonna probably need a function somehwere that converts newUsername to
        // username

    }

/**
* Main menu of the system. User can do many different things from here including Search for a flight/hotl or view booking history
*/
    private void mainMenu(){
        printHeading(" Main Menu ");
        System.out.println("Please choose from the following options:");
        for (int i = 0; i < mainMenuOptions.length; i++) {
            System.out.println((i + 1) + ". " + mainMenuOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(mainMenuOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    searchForFlight();
                    break;
                case (1):
                    searchForHotel();
                    break;
                case (2):
                    viewBookingHistory();
                    break;
                case (3):
                    exit();
                    break;
            }
        }
    }

    // **********BENEATH IS ALL THE FLIGHT SEARCH METHODS******************

    //**********BENEATH IS ALL THE FLIGHT SEARCH METHODS******************

/**
* User inputs all their desired information for searching a flight
*/
    private void searchForFlight(){

        printHeading(" Flight Search ");
        System.out.println("Roundtrip: (Y/N)");
        // next 2 lines record Y/N from scanner.nextLine
        String roundtripInput = scanner.nextLine();
        this.roundTrip = roundtripInput.toLowerCase().equals("y");
        System.out.println("Departing Airport Code:");
        this.departingCode = scanner.nextLine().toUpperCase();
        System.out.println("Arrival Airport Code:");
        this.arrivalCode = scanner.nextLine().toUpperCase();
        System.out.println("Number of Passengers:");
        int passengers = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Number of Checked Bags: ");
        this.checkedBags = scanner.nextInt();
        scanner.nextLine();

        this.guests = new ArrayList<Guest>();
        if (passengers > 1)
            guests = enterGuestInfo(passengers - 1);

        for (int i = 0; i < nextPageOptions.length; i++) {
            System.out.println((i + 1) + ". " + nextPageOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(nextPageOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    if (roundTrip) {
                        roundtripFlightSearch(this.departingCode, this.arrivalCode, this.guests, this.checkedBags);
                        break;
                    } else {
                        onewayFlightSearch(this.departingCode, this.arrivalCode, this.guests, this.checkedBags);
                        break;
                    }

                case (1):
                    searchForFlight();
                    break;
            }
        }
    }

/**
* User inputs departing and return dates for a roundtrip flight
* @param departingCode
* @param arrivalCode
* @param guests
* @param checkedBags
*/
    private void roundtripFlightSearch(String departingCode, String arrivalCode, ArrayList<Guest> guests, int checkedBags){
        printHeading(" Flight Search ");
        System.out.println("Departure Date: (mm/dd/yyyy)");
        this.departureDate = LocalDate.parse(scanner.nextLine(), FORMATTER);
        System.out.println("Return Date: (mm/dd/yyyy)");
        this.returnDate = LocalDate.parse(scanner.nextLine(), FORMATTER);

        ArrayList<Flight> flightSearchResults = facade.searchForFlights(this.departingCode, this.arrivalCode);

        for (int i = 0; i < nextPageOptions.length; i++) {
            System.out.println((i + 1) + ". " + nextPageOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(nextPageOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    flightFilter(flightSearchResults);
                    break;
                case (1):
                    searchForFlight();
                    break;
            }
        }
    }
  
/**
* User inputs only departing dates for a oneway flight
* @param departingCode
* @param arrivalCode
* @param guests
* @param checkedBags
*/
    private void onewayFlightSearch(String departingCode, String arrivalCode, ArrayList<Guest> guests, int checkedBags){
        printHeading(" Flight Search ");
        System.out.println("Departure Date: (mm/dd/yyyy)");
        this.departureDate = LocalDate.parse(scanner.nextLine(), FORMATTER);

        ArrayList<Flight> flightSearchResults = facade.searchForFlights(this.departingCode, this.arrivalCode);

        for (int i = 0; i < nextPageOptions.length; i++) {
            System.out.println((i + 1) + ". " + nextPageOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(nextPageOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):

                    flightFilter(flightSearchResults);
                    break;

                case (1):
                    searchForFlight();
                    break;
            }
        }
    }
    
/**
* User can sort his flight search results in 3 different ways 
* @param flightSearchResults
*/
    private void flightFilter(ArrayList<Flight> flightSearchResults){
        printHeading(" Flight Filter ");
        System.out.println("How would you like your search results to be sorted?");

        for (int i = 0; i < flightFilterOptions.length; i++) {
            System.out.println((i + 1) + ". " + flightFilterOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(flightFilterOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    // sort price
                    this.flightTrait = FlightTrait.valueOf("PRICE");
                    if (roundTrip && !chosenReturnFlight && chosenDepartureFlight)
                        returnFlightResults();
                    departingFlightResults(this.flightTrait, flightSearchResults);
                    break;

                case (1):
                    // sort duration
                    this.flightTrait = FlightTrait.valueOf("DURATION");
                    if (roundTrip && !chosenReturnFlight && chosenDepartureFlight)
                        returnFlightResults();
                    departingFlightResults(this.flightTrait, flightSearchResults);
                    break;
                case (2):
                    // sort departure time
                    this.flightTrait = FlightTrait.valueOf("TAKEOFF_TIME");
                    if (roundTrip && !chosenReturnFlight && chosenDepartureFlight)
                        returnFlightResults();
                    departingFlightResults(this.flightTrait, flightSearchResults);
                    break;
                case (3):
                    if (roundTrip) {
                        roundtripFlightSearch(this.departingCode, this.arrivalCode, this.guests, this.checkedBags);
                        break;
                    } else {
                        onewayFlightSearch(this.departingCode, this.arrivalCode, this.guests, this.checkedBags);
                        break;
                    }
            }
        }
    }

 /**
* Displays 3 flight results based off the information entered in "searchForFlight". User chooses a flight it is saved to their account as their departing flight. 
* @param flightTrait
* @param unfilteredFlights
*/   
    private void departingFlightResults(FlightTrait flightTrait, ArrayList<Flight> unfilteredFlights){
        printHeading(" Departing Flight Results ");

        this.unfilteredFlights = unfilteredFlights;
        ArrayList<Flight> filteredFlights = facade.filterFlights(flightTrait, unfilteredFlights);

        // SHOW DEPARTING FLIGHT SEARCH RESULTS HERE

        for (int i = 0; i < 3; i++) {
            System.out.println("FLIGHT " + (i + 1));
            System.out.println(filteredFlights.get(i));
        }

        // HOW DO YOU RECORD AND SAVE THE FLIGHT

        for (int i = 0; i < chooseFlightOptions.length; i++) {
            System.out.println((i + 1) + ". " + chooseFlightOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(chooseFlightOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    // Flight 1 chosen
                    chosenDepartureFlight = true;
                    chooseSeats(filteredFlights.get(0));
                    break;

                case (1):
                    // Flight 2 chosen
                    chosenDepartureFlight = true;
                    chooseSeats(filteredFlights.get(1));
                    break;
                case (2):
                    // Flight 3 chosen
                    chosenDepartureFlight = true;
                    chooseSeats(filteredFlights.get(2));
                    break;
                case (3):
                    flightFilter(unfilteredFlights);
                    break;
                case (4):
                    searchForFlight();
                    break;
                default:
                    System.out.println("Default case");
            }
        }
    }

/**
* If roundtrip flight, Displays 3 returning flight results based off the information entered in "searchForFlight". User chooses a flight it is saved to their account. 
*/
    private void returnFlightResults(){
        printHeading(" Return Flight Results ");

        ArrayList<Flight> flightSearchResults = facade.searchForFlights(this.arrivalCode, this.departingCode);

        ArrayList<Flight> filteredReturnFlights = facade.filterFlights(flightTrait, flightSearchResults);

        // DISPLAY RETURNING FLIGHT RESULTS HERE

        int iterationAmount = filteredReturnFlights.size() < 4 ? filteredReturnFlights.size() : 4;

        for (int i = 0; i < iterationAmount; i++) {
            System.out.println("FLIGHT " + (i + 1));
            System.out.println(filteredReturnFlights.get(i));
        }

        // HOW DO YOU RECORD AND SAVE THE FLIGHT

        for (int i = 0; i < chooseFlightOptions.length; i++) {
            System.out.println((i + 1) + ". " + chooseFlightOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(chooseFlightOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    // Flight 1 chosen
                    chosenReturnFlight = true;
                    chooseSeats(filteredReturnFlights.get(0));
                    break;

                case (1):
                    // Flight 2 chosen
                    chosenReturnFlight = true;
                    chooseSeats(filteredReturnFlights.get(1));
                    break;
                case (2):
                    // Flight 3 chosen
                    chosenReturnFlight = true;
                    chooseSeats(filteredReturnFlights.get(2));
                    break;
                case (3):
                    flightFilter(flightSearchResults);
                    break;
                case (4):
                    searchForFlight();
                    break;

            }
        }
    }

/**
* System displays and the user chooses their desired seats from the list of available seats on that flight
* @param flight
*/    
    private void chooseSeats(Flight flight){
        printHeading(" Choose Seats ");
        System.out.println("Select your desired seats from the list of available seats below.");
        ArrayList<Flight> flightsToChooseSeatsFor = new ArrayList<Flight>();
        flightsToChooseSeatsFor.add(flight);

        if (!guests.isEmpty() && guests != null)
            System.out.println(
                    "You have indicated that you have guests. After choosing your own seat, you will choose a seat for each guest as well.");
        if (!flight.getConnections().isEmpty() && flight.getConnections() != null) {
            System.out.println(
                    "The flight you chose has connecting flights. You will be prompted to choose a seat for each flight.");
            ArrayList<Flight> flightConnections = flight.getConnections();
            for (Flight connectingFlight : flightConnections) {
                flightsToChooseSeatsFor.add(connectingFlight);
            }
        }

        for (Flight selectedFlight : flightsToChooseSeatsFor) {
            String flightPath = "";
            if (selectedFlight.getConnections() != null && !selectedFlight.getConnections().isEmpty()
                    && selectedFlight.equals(flightsToChooseSeatsFor.get(0))) {
                flightPath = selectedFlight.getDepartureCode() + " --> "
                        + flightsToChooseSeatsFor.get(1).getDepartureCode();
            } else
                flightPath = selectedFlight.getDepartureCode() + " --> " + selectedFlight.getArrivalCode();

            System.out.println("\nFLIGHT: " + flightPath);
            ArrayList<Seat> availableSeats = selectedFlight.getAvailableSeats();
            int seatChoiceIterations;
            if (guests.isEmpty() || guests == null)
                seatChoiceIterations = 1;
            else
                seatChoiceIterations = guests.size() + 1;

            int currentUserSeatID = 0;

            for (int i = 0; i < seatChoiceIterations; i++) {

                if (i > 0)
                    System.out.println(
                            "\nPlease choose a seat for your guest, " + guests.get(i - 1).getFirstName() + ":");

                System.out.println("\nFirst Class Seats:");
                for (Seat seat : availableSeats) {
                    if (SeatClass.FIRST == seat.getSeatClass())
                        System.out.println(
                                seat.getSeatNumber() + " (" + seat.getSeatType().toString().toLowerCase() + ")");
                }

                System.out.println("\nBusiness Class Seats:");
                for (Seat seat : availableSeats) {
                    if (SeatClass.BUSINESS == seat.getSeatClass())
                        System.out.println(
                                seat.getSeatNumber() + " (" + seat.getSeatType().toString().toLowerCase() + ")");
                }

                System.out.println("\nEconomy Class Seats:");
                for (Seat seat : availableSeats) {
                    if (SeatClass.ECONOMY == seat.getSeatClass())
                        System.out.println(
                                seat.getSeatNumber() + " (" + seat.getSeatType().toString().toLowerCase() + ")");
                }

                String userInput = scanner.nextLine();

                ArrayList<String> availableSeatNums = new ArrayList<String>();
                for (Seat seat : availableSeats) {
                    availableSeatNums.add(seat.getSeatNumber());
                }

                while (!availableSeatNums.contains(userInput)) {
                    System.out.println("Sorry, that seat isn't available. Please choose a different seat.");
                    userInput = scanner.nextLine();
                }

                if (i > 0)
                    guests.get(i - 1).setSeatID(selectedFlight.getSeatBySeatNumber(userInput).getSeatID());
                else
                    currentUserSeatID = selectedFlight.getSeatBySeatNumber(userInput).getSeatID();

                // remove seat so you can't choose the same seat twice
                availableSeats.remove(selectedFlight.getSeatBySeatNumber(userInput));
            }

            FlightBooking flightBooking = new FlightBooking(guests, selectedFlight.getFlightID(), currentUserSeatID,
                    this.checkedBags);
            flightBookingsInCart.add(flightBooking);
        }

        if (roundTrip && !chosenReturnFlight)
            returnFlightResults();
        else
            flightCheckout();

    }

/**
* System shows the user their flight bookings currently in their cart. User can choose to purchase these bookngs and save to their account, or go back to search results
*/  
    private void flightCheckout(){
        printHeading(" Flight Checkout ");

        System.out.println("You have the following flight bookings in your cart currently:");
        for (int i = 0; i < flightBookingsInCart.size(); i++) {
            FlightBooking booking = flightBookingsInCart.get(i);
            System.out.println((i + 1) + ". " + booking.toString() + "\n");
        }

        for (int i = 0; i < checkoutOptions.length; i++) {
            System.out.println((i + 1) + ". " + checkoutOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(checkoutOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch(userCommand) {
                case(0):
                    if(facade.getCurrentUser() == null){
                        redirectGuestToFlightCheckout = true;
                        guestError();
                        break;
                    } else {
                        for (FlightBooking booking : flightBookingsInCart) { // for each booking in shopping cart, add
                                                                             // to user's booking history
                            facade.bookFlight(booking);
                        }
                        completedFlightBooking();
                        break;
                    }
                case (1):
                    chosenDepartureFlight = false;
                    chosenReturnFlight = false;
                    flightBookingsInCart = new ArrayList<FlightBooking>();
                    searchForFlight();
                    break;

            }
        }
    }

/**
* User successfully completes a booking and can either continue or exit the system
*/
    private void completedFlightBooking(){
        printHeading(" Booking Confirmation ");
        System.out.println("Thank you for choosing Canoe.\nYour booking has been saved to your account");

        for (int i = 0; i < completedFlightBookingOptions.length; i++) {
            System.out.println((i + 1) + ". " + completedFlightBookingOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(completedFlightBookingOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    mainMenu();
                    break;

                case (1):
                    viewBookingHistory();
                    break;

                case (2):
                    searchForHotelPostFlight();
                    break;

                case (3):
                    exit();
                    break;
            }
        }
    }

    /**
    * BENEATH IS ALL THE HOTEL SEARCH METHODS
    */

/**
* User inputs all of their desired information for choosing a hotel
*/    
    private void searchForHotel(){
        printHeading(" Hotel Search ");
        System.out.println("Please input your desired locations, check-in, and check-out dates below.");
        System.out.println("Nearby Airport Code:");
        this.hotelCode = scanner.nextLine().toUpperCase(); // would the variable be this or something different because
                                                           // the only other variables are "departingCode" and
                                                           // "arrivalCode"
        System.out.println("Check-in Date: (mm/dd/yyyy)");
        this.checkIn = LocalDate.parse(scanner.nextLine(), FORMATTER);
        System.out.println("Check-out Date: (mm/dd/yyyy)");
        this.checkOut = LocalDate.parse(scanner.nextLine(), FORMATTER);

        hotelSearchResults();
    }

/**
* User can choose from a King, Queen, or Double bed in the hotel.
*/  
    private void selectRoomSize(){
        printHeading(" Room Selection ");
        System.out.println("Please choose your desired room size:");

        for (int i = 0; i < roomSizeOptions.length; i++) {
            System.out.println((i + 1) + ". " + roomSizeOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(roomSizeOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    // king bed chosen
                    this.bedType = BedType.valueOf("KING");
                    hotelCheckout();
                    break;

                case (1):
                    // queen bed chosen
                    this.bedType = BedType.valueOf("QUEEN");
                    hotelCheckout();
                    break;
                case (2):
                    // double bed chosen
                    this.bedType = BedType.valueOf("DOUBLE");
                    hotelCheckout();
                    break;
            }
        }
    }

    /**
     * Selects room from chosen hotel and saves the roomId
     */
    private void selectRoom() {
        printHeading(" Room Selection ");
        System.out.println("Please choose your desired room:");

        for (int i = 0; i < this.chosenHotel.getRooms().size(); i++) {
            System.out.println(this.chosenHotel.getRooms().get(i).toString());
        }
        while (true) {
            int userCommand = getUserSelection(this.chosenHotel.getRooms().size());

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    this.hotelRoomID = 1;
                    hotelCheckout();
                    break;

                case (1):
                    this.hotelRoomID = 2;
                    hotelCheckout();
                    break;
                case (2):
                    this.hotelRoomID = 3;
                    hotelCheckout();
                    break;
                case (3):
                    this.hotelRoomID = 4;
                    hotelCheckout();
                    break;
                case(4):
                    this.hotelRoomID = 5;
                    hotelCheckout();
                    break;
            }
        }
    }

    /**
* Allows the user to search for a hotel, directly after booking a flight. System will save the arrival airport code and will search for hotels near that airport.
*/
    private void searchForHotelPostFlight() {
        printHeading(" Hotel Search ");
        System.out.println("Please input your desired checkout date below");
        System.out.println("Check-out Date: (mm/dd/yyyy)");
        this.hotelCode = this.arrivalCode;
        this.checkIn = this.departureDate;
        this.checkOut = LocalDate.parse(scanner.nextLine(), FORMATTER);
        hotelSearchResults();
    }

/**
* Displays 3 hotel search results based on the information inputted above. 
*/
    private void hotelSearchResults(){
        printHeading(" Hotel Search Results ");
        System.out.println(
                "Please select a hotel from the list below. Then you will be asked what type of room you would like.");

        // DISPLAY HOTELS HERE

        ArrayList<Hotel> hotelResults = facade.searchForHotels(this.hotelCode);

        for (int i = 0; i < hotelResults.size(); i++) {
            System.out.println((i + 1) + ". " + hotelResultsOptions[i]);
            System.out.println(hotelResults.get(i));
        }
        while (true) {
            int userCommand = getUserSelection(hotelResultsOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    this.chosenHotel = hotelResults.get(0);
                    selectRoom();
                    break;

                case (1):
                    this.chosenHotel = hotelResults.get(2);
                    selectRoom();
                    break;
                case (2):
                    this.chosenHotel = hotelResults.get(2);
                    selectRoom();
                    break;
                case (3):
                    mainMenu();
                    break;
            }
        }
    }

/**
* Shows the user what is in their cart and gives them the option to purchase the hotel room
*/
    private void hotelCheckout(){
        printHeading(" Hotel Checkout ");

        System.out.println(this.chosenHotel);
        System.out.println(this.chosenHotel.getRoomByID(this.hotelRoomID).toString());
        // facade.bookHotelRoom(this.chosenHotel.getHotelID(),
        // chosenHotelRoom.getRoomID(), this.checkIn, this.checkOut);

        for (int i = 0; i < checkoutOptions.length; i++) {
            System.out.println((i + 1) + ". " + checkoutOptions[i]);
        }

        while (true) {
            int userCommand = getUserSelection(checkoutOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    if (facade.getCurrentUser() == null) {
                        guestError();
                        break;
                    } else {
                        facade.bookHotelRoom(this.chosenHotel.getHotelID(), this.hotelRoomID, this.checkIn, this.checkOut);
                        completedHotelBooking();
                        break; // SAVE NEW BOOKING
                    }
                case (1):
                    hotelSearchResults();
                    break;
            }
        }
    }

/**
* Completion of booking a hotel room. The booking is saved to users account
*/
    private void completedHotelBooking(){
        printHeading(" Booking Confirmation ");
        System.out.println("Thank you for choosing Canoe.\nYour booking has been saved to your account");

        for (int i = 0; i < completedHotelBookingOptions.length; i++) {
            System.out.println((i + 1) + ". " + completedHotelBookingOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(completedHotelBookingOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch (userCommand) {
                case (0):
                    mainMenu();
                    break;

                case (1):
                    viewBookingHistory();
                    break;

                case (3):
                    exit();
                    break;
            }
        }
    }

/**
 * From the main menu, this section allows a logged in user to view all of their completed flight or hotel bookings
 */
    private void viewBookingHistory(){
        printHeading(" Booking History ");
        System.out.println("You may view your booking history below or choose from one of the following options.\n");

        ArrayList<FlightBooking> flightBookings = facade.getBookingHistory();
        ArrayList<HotelBooking> hotelBookings = facade.getHotelBookingHistory();

        System.out.println("\nFLIGHT BOOKING HISTORY\n");
        for (FlightBooking booking : flightBookings) {
            System.out.println(booking + "\n");
        }

        System.out.println("\nHOTEL BOOKING HISTORY\n");
        for (HotelBooking booking : hotelBookings) {
            System.out.println(booking + "\n");
        }

        for (int i = 0; i < bookingHistoryptions.length; i++) {
            System.out.println((i + 1) + ". " + bookingHistoryptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(bookingHistoryptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            if (userCommand == bookingHistoryptions.length - 1)
                exit();

            switch (userCommand) {
                case (0):

                    mainMenu();
                    break;

                case (1):
                    exit();
                    break;
            }
        }

        // SHOW BOOKING HISTORY HERE

    }

/**
 * Users are prompted to this page when they try to checkout as a guest. Then gives them the option to login, create an account, or exit the system
 */
    private void guestError(){
        printHeading(" Guest Error ");
        System.out.println("You are currently using a guest account and therefore don't have access to this feature.");
        System.out.println("Please choose from the following options:");
        for (int i = 0; i < guestErrorOptions.length; i++) {
            System.out.println((i + 1) + ". " + guestErrorOptions[i]);
        }
        while (true) {
            int userCommand = getUserSelection(guestErrorOptions.length);

            if (userCommand == -1) {
                System.out.println("Not a valid command");
                continue;
            }

            switch(userCommand) {
                case(0):
                        logIn();
                        break;
                case(1):
                        createAccount();
                        break;
                case(2):
                        exit();
                        break;
              }
        }
    }

/**
* When users are booking flights for other guests, they are prompted to this page to enter their guests information.
* @param numGuests
* @return
*/
    public ArrayList<Guest> enterGuestInfo(int numGuests) {
        printHeading(" Guest Info ");

        System.out.println(
                "You indicated you have additional passengers. Please enter the information for your passengers.");

        ArrayList<Guest> guests = new ArrayList<Guest>();

        for (int i = 0; i < numGuests; i++) {

            System.out.println("Guest " + (i + 1) + " First Name:");
            String firstName = scanner.nextLine();
            System.out.println("Guest " + (i + 1) + " Last Name:");
            String lastName = scanner.nextLine();
            System.out.println("Guest " + (i + 1) + " Age:");
            int age = scanner.nextInt();
            scanner.nextLine();

            guests.add(new Guest(age, firstName, lastName, -1));
        }

        return guests;
    }

/**
 * User exits/quits the system.
 */
    private void exit(){
        printHeading(" Canoe Booking ");

        try {
            facade.logOut();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Thank you for choosing Canoe Booking! Happy travels!\n");

        System.exit(0);
    }

    public static void main(String[] args) {
        UserInterface UI = new UserInterface();
        UI.run();
    }
}