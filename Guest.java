import java.util.UUID;

public class Guest {
    private String name;
    private int age;
    private UUID seatID;
    private boolean needSeat;

    public Guest(int age, String firstName, String lastName){

    }

    public String getName(){
        return this.name;
    }

    public int getAge(){
        return this.age;
    }

    public UUID getSeatID(){
        return this.seatID;
    }

    public boolean getNeedSeat(){
        return this.needSeat;
    }


    public void setName(String name){
        this.name = name;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setSeatID(UUID seatID){
        this.seatID = seatID;
    }

    public void setNeedSeat(boolean needSeat){
        this.needSeat = needSeat;
    }

 

}
