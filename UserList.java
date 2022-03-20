import java.util.ArrayList;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    private UserList(){

    }

    public static UserList getInstance(){
        if (userList == null) userList = new UserList();
        return userList;

    }

    public User getUser(String userName){
        return null;
    }


}
