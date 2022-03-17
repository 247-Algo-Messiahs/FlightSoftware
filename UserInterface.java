public class UserInterface {
    private UserInterface userInterface;

    private UserInterface() {

    }

    public UserInterface getInstance() {
        if (this.userInterface == null) this.userInterface = new UserInterface();
        return this.userInterface;
    }
}