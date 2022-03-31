/**
 * @author Avery Slomnicki, Austin Hanson, Peyton Tucker
 * A class to keep track of guests who are accompanying user objects of the RegisteredUser class on flights
 */

public class Guest extends User{
    private int seatID;
    private boolean needSeat;

    /**
     * Constructs a new instance of the Guest class
     * @param age the age of the guest
     * @param firstName the first name of the guest
     * @param lastName the last name of the guest
     * @param seatID the seatID of the guest's seat on the flight they are taking
     */
    public Guest(int age, String firstName, String lastName, int seatID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.seatID = seatID;
    }

    /**
     * Returns the name of this Guest object
     * @return the name of this Guest object
     */
    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    /**
     * returns the age of the guest this Object represents
     * @return this guest object's age value
     */
    public int getAge(){
        return this.age;
    }

    /**
     * Gets the seatID of this guest
     * @return this object's seatID
     */
    public int getSeatID(){
        return this.seatID;
    }

    /**
     * Sets the seatID of this Guest object
     * @param seatID the seatID to set
     */
    public void setSeatID(int seatID){
        this.seatID = seatID;
    }


 

}
