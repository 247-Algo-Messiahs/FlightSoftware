/**
 * @author Peyton Tucker
 * A singleton class to hold a list of User objects
 */

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * UserList object full of RegisteredUser objects
 */
public class UserList {
    private static UserList userList;
    private static ArrayList<RegisteredUser> users;

    /**
     * A private constructor to follow the singleton design pattern. Loads all users and their bookings.
     */
    private UserList(){
        users = DataLoader.loadUsers();
        loadUserBookings();
        
    }

    /**
     * Loads the flight and hotel bookings of all users in this singleton's list of user objects.
     */
    public static void loadUserBookings() {
        for (RegisteredUser user : users) {
            DataLoader.loadUserFlightBookings(user);
            DataLoader.loadUserHotelBookings(user);
        }
    }

    /**
     * Returns this singleton's instance
     * @return this singleton's instance
     */
    public static UserList getInstance(){
        if (userList == null) userList = new UserList();
        return userList;

    }

    /**
     * Adds a user to this object's list of all user objects
     * @param user
     */
    public static void addUserToList(RegisteredUser user) {
        users.add(user);
    }

    /**
     * Sets this UserList object's list of users
     * @param usersArrayList the ArrayList of users to set
     */
    public void setUsers(ArrayList<RegisteredUser> usersArrayList) {
        users = usersArrayList;
    }

    /**
     * Returns this singleton's list of users
     * @return this singleton's ArrayList of users
     */
    public ArrayList<RegisteredUser> getUsers() {
        return users;
    }

    /**
     * Finds a user in this singleton's user list whose UUID matches the one provided
     * @param uuid the UUID of the RegisteredUser object to search for
     * @return the RegisteredUser object whose UUID matches this one
     */
    public static RegisteredUser getRegisteredUserByUUID(UUID uuid) {
        for (int i = 0; i < users.size(); i++) {
            RegisteredUser selectedUser = users.get(i);
            if (selectedUser.getUserID().equals(uuid)) return selectedUser;
        }
        return null;
    }

    /**
     * Print all users that are stored in this list
     */
    public void printUsers(){
        for(int i=0; i<users.size(); i++){
            System.out.println(users.get(i));
        }
    }

    /**
     * Calls the DataWriter to save this UserList's list of users to users.json
     * @throws IOException if an error occurs when DataWriter attempts to save users
     */
    public void saveUsers() throws IOException{
        DataWriter.saveUsers();
    }
}
