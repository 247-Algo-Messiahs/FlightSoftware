import java.util.ArrayList;
import java.util.UUID;

public class UserList {
    private static UserList userList;
    private ArrayList<RegisteredUser> users;

    private UserList(){

    }

    public static UserList getInstance(){
        if (userList == null) userList = new UserList();
        return userList;

    }

    public User getUser(String userName){
        return null;
    }

    public void setUsers(ArrayList<RegisteredUser> users) {
        this.users = users;
    }

    public ArrayList<RegisteredUser> getUsers() {
        return this.users;
    }

    public RegisteredUser getUserByUUID(UUID uuid) {
        for (int i = 0; i < users.size(); i++) {
            RegisteredUser selectedUser = users.get(i);
            if (selectedUser.getUserID().equals(uuid)) return selectedUser;
        }
        return null;
    }
}
