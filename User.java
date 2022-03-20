public class User {
    private int age;
    private String firstName;
    private String lastName;

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

}
