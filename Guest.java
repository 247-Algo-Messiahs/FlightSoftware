import java.util.UUID;

public class Guest extends User{
    private int seatID;
    private boolean needSeat;

    public Guest(int age, String firstName, String lastName, int seatID){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.seatID = seatID;
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    public int getAge(){
        return this.age;
    }

    public int getSeatID(){
        return this.seatID;
    }

    public boolean getNeedSeat(){
        return this.needSeat;
    }

    public void setSeatID(int seatID){
        this.seatID = seatID;
    }

    public void setNeedSeat(boolean needSeat){
        this.needSeat = needSeat;
    }

 

}
