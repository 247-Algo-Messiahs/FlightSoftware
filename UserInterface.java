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