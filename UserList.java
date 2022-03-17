import java.util.ArrayList;

public class UserList {
    private static UserList userList;
    private ArrayList<User> users;

    private UserList(){

    }

    public UserList getInstance(){
        if (this.userList == null) this.userList = new UserList();
        return this.userList;

    }

    public User getUser(String userName){
        return null;
    }


}
