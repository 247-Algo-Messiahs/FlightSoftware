public class User {
    protected int age;
    protected String firstName;
    protected String lastName;
    protected Preferences preferences;

    public User() {
        this.firstName = "NONE_GIVEN";
        this.lastName = "NONE_GIVEN";
        this.age = 0;
    }

    public User(int age, String firstName, String lastName){
        this.age = age;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getAge() {
        return this.age;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Preferences getPreferences() {
        return this.preferences;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPreferences(Preferences preferences) {
        this.preferences = preferences;
    }
}
