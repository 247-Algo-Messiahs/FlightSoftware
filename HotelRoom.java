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
    
    public HotelRoom(int roomID, ArrayList<LocalDate> notAvail, BedType bedType, boolean isSmoking, boolean hasBalcony, boolean hasPulloutCouch){
        this.roomID = roomID;
        this.notAvail = notAvail;
        this.bedType = bedType;
        this.isSmoking = isSmoking;
        this.hasBalcony = hasBalcony;
        this.hasPulloutCouch = hasPulloutCouch;
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
}
