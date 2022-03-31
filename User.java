/**
 * A parent class for extension by different types of User classes, namely RegisteredUser and Guest
 * @author Peyton Tucker
 */

public class User {
    protected int age;
    protected String firstName;
    protected String lastName;

    /**
     * Constructs a new User object with default values for firstName, lastName, and age
     */
    public User() {
        this.firstName = "NONE_GIVEN";
        this.lastName = "NONE_GIVEN";
        this.age = 0;
    }

    /**
     * Parameterized constructor that instantiates a new User object
     * @param age the age of this user
     * @param firstName the first name of this user
     * @param lastName the last name of this user
     */
    public User(int age, String firstName, String lastName){
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    /**
     * The age of this user.
     * @return the age of this user
     */
    public int getAge() {
        return this.age;
    }

    /**
     * Returns this user's first name.
     * @return this user's first name
     */
    public String getFirstName() {
        return this.firstName;
    }

    /**
     * Returns this user's last name.
     * @return this user's last name
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * Sets this user's age
     * @param age the age to set
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets this user's first name
     * @param firstName the first name to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Sets this user's last name
     * @param lastName the last name to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
