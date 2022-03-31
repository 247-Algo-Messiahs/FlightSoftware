import java.time.LocalDate;
import java.util.ArrayList;

/**
 * @author Nicolas Becker, Peyton Tucker, Avery Slomnicki, Austin Hanson
 */
public class HotelRoom {
    private int roomID;
    private ArrayList<LocalDate> notAvail;
    private BedType bedType;
    private boolean isSmoking;
    private boolean hasBalcony;
    private boolean hasPulloutCouch;
    private int price;
    
    /**
     * Defines HotelRoom objects
     * @param roomID
     * @param price
     * @param notAvail
     * @param bedType
     * @param isSmoking
     * @param hasBalcony
     * @param hasPulloutCouch
     */
    public HotelRoom(int roomID, int price, ArrayList<LocalDate> notAvail, BedType bedType, boolean isSmoking, boolean hasBalcony, boolean hasPulloutCouch){
        this.roomID = roomID;
        this.notAvail = notAvail;
        this.bedType = bedType;
        this.isSmoking = isSmoking;
        this.hasBalcony = hasBalcony;
        this.hasPulloutCouch = hasPulloutCouch;
        this.price = price;
    }

    /**
     * @return int of given roomID
     */
    public int getRoomID(){
        return this.roomID;
    }

    /**
     * 
     * @return int of given room price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     * 
     * @return ArrayList of LocalDate items that a given room is unnavailable
     */
    public ArrayList<LocalDate> getNotAvail(){
        return this.notAvail;
    }

    /**
     * Sets list of dates that a given room isn't available
     * @param notAvail
     */
    public void setNotAvail(ArrayList<LocalDate> notAvail){
        this.notAvail = notAvail;
    }

    /**
     * 
     * @return boolean of if a room is smoking
     */
    public boolean isSmoking(){
        return this.isSmoking;
    }

    /**
     * 
     * @return BedType of given room
     */
    public BedType getBedType(){
        return this.bedType;
    }

    /**
     * 
     * @return boolean of if given room has a balcony
     */
    public boolean hasBalcony(){
        return this.hasBalcony;
    }

    /**
     * 
     * @return boolean of if given room has pullout couch
     */
    public boolean hasPulloutCouch(){
        return this.hasPulloutCouch;
    }

    /**
     * @return String representation of list of not available dates for given room
     */
    public String notAvailDatesList() {
        String list = "";
        for (int i = 0; i < notAvail.size(); i++) {
            list += notAvail.get(i) + "\n";
        }
        return list;
    }

    /**
     * adds set of dates to a given rooms non-availability
     * @param datesToAdd
     */
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
