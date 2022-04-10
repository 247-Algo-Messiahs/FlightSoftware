import static org.junit.jupiter.api.Assertions.*;

import java.beans.Transient;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DataWriterTest{
    
    private UserList userList = UserList.getInstance();
    private ArrayList<RegisteredUser> allUsers = userList.getUsers();

    @BeforeEach
    public void setup() throws IOException{
        userList.getInstance().getUsers().clear();

        DataWriter.saveUsers();
    }

    @AfterEach
    public void tearDown() throws IOException{
        userList.getInstance().getUsers().clear();

        DataWriter.saveUsers();
    }

    @Test
    void testWritingZeroUsers(){
        allUsers = DataLoader.loadUsers();
        assertEquals(0, allUsers.size());
    }

    @Test
    void testWritingOneUser(){
        UUID num = UUID.randomUUID();
        RegisteredUser user = new RegisteredUser(num, "Bob", "Johnson", "123 Road House", "123-4567", "namename", "password", true, 38, "namename@email.com");
        userList.addUserToList(user);
        assertEquals("Bob", DataLoader.loadUsers().get(0).getFirstName());
    }

    @Test
    void testWritingTwoUsers(){
        UUID num = UUID.randomUUID();
        RegisteredUser user = new RegisteredUser(num, "Bob", "Johnson", "123 Road House", "123-4567", "namename", "password", true, 38, "namename@email.com");
        userList.addUserToList(user);
        num = UUID.randomUUID();
        RegisteredUser user1 = new RegisteredUser(num, "Rain", "Boy", "123 House House", "123-4567", "namename", "password", true, 38, "namename@email.com");
        userList.addUserToList(user1);
        assertEquals("Rain", DataLoader.loadUsers().get(1).getFirstName());
    }

    @Test
    void testWritingEmptyUser(){
        UUID num = UUID.randomUUID();
        userList.addUserToList(new RegisteredUser(num, "", "", "", "", "", "", false, 0, ""));
        assertEquals("", DataLoader.loadUsers().get(0).getUsername());
    }

    @Test
    void testWritingNullUser(){
        UUID num = UUID.randomUUID();
        userList.addUserToList(new RegisteredUser(num, "", "", "", "", "", "", false, 0, ""));
        assertEquals(null, DataLoader.loadUsers().get(0).getUsername());
    }
}
