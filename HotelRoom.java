import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import javax.lang.model.type.NoType;

import org.w3c.dom.Notation;

public class HotelRoom {
    private int roomID;
    private ArrayList<LocalDate> notAvail;
    private BedType bedType;
    private boolean isSmoking;
    private boolean hasBalcony;
    private boolean hasPulloutCouch;
    private int price;
    
    public HotelRoom(int roomID, int price, ArrayList<LocalDate> notAvail, BedType bedType, boolean isSmoking, boolean hasBalcony, boolean hasPulloutCouch){
        this.roomID = roomID;
        this.notAvail = notAvail;
        this.bedType = bedType;
        this.isSmoking = isSmoking;
        this.hasBalcony = hasBalcony;
        this.hasPulloutCouch = hasPulloutCouch;
        this.price = price;
    }

    public int getRoomID(){
        return this.roomID;
    }

    public int getPrice() {
        return this.price;
    }

    public ArrayList<LocalDate> getNotAvail(){
        return this.notAvail;
    }

    public void setNotAvail(ArrayList<LocalDate> notAvail){
        this.notAvail = notAvail;
    }

    public boolean isSmoking(){
        return this.isSmoking;
    }

    public BedType getBedType(){
        return this.bedType;
    }

    public boolean hasBalcony(){
        return this.hasBalcony;
    }

    public boolean hasPulloutCouch(){
        return this.hasPulloutCouch;
    }

    public String notAvailDatesList() {
        String list = "";
        for (int i = 0; i < notAvail.size(); i++) {
            list += notAvail.get(i) + "\n";
        }
        return list;
    }

    public void addToNotAvail(ArrayList<LocalDate> datesToAdd) {
        for (LocalDate date : datesToAdd) {
            this.notAvail.add(date);
        }
    }

    @Override
    public String toString() {
        return "Room " + this.roomID + "\nBed: " + this.bedType + "   Price: $" + this.price + "\nAmeneties:\nSmoking: " + this.isSmoking + "   Balcony: " + this.hasBalcony + "    Pullout couch: " + this.hasPulloutCouch;
    }
}
