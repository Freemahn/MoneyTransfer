import com.google.gson.Gson;
import entity.User;
import exception.UserException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.jupiter.api.Test;
import service.UserService;
import service.impl.UserServiceMapImpl;

import java.io.IOException;
import java.util.ArrayList;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * @author Pavel Gordon
 */
public class UserTests {

    private UserService userService = UserServiceMapImpl.getInstance();
    private String prefix = "http://localhost:4567";


    @Test
    public void testCreateUser() {
        User user = userService.addUser(new User("Ivan", "Ivan", "gordon.pav@gmail.com", new ArrayList<>()));
        assertThat(userService.getUser(user.getId()), equalTo(user));
        userService.deleteUser(user.getId());
    }

    @Test
    public void testUpdateUser() throws UserException {
        User user = userService.addUser(new User("Ivanm", "Ivanovm", "ivanm.ivanovm@gmail.com", new ArrayList<>()));
        user.setFirstName("Ivan");
        user.setLastName("Ivanov");
        user.setEmail("ivan.ivanov@gmail.com");
        userService.editUser(user);
        user = userService.getUser(user.getId());
        assertThat(user.getFirstName(), equalTo("Ivan"));
        assertThat(user.getLastName(), equalTo("Ivanov"));
        assertThat(user.getEmail(), equalTo("ivan.ivanov@gmail.com"));
        userService.deleteUser(user.getId());
    }

    @Test
    public void testDeleteUser() {
        User user = userService.addUser(new User("Petr", "Petrov", "petr.petrov@gmail.com", new ArrayList<>()));
        userService.deleteUser(user.getId());
        assertNull(userService.getUser(user.getId()));
    }
}
