import controllers.AccountEndpoints;
import controllers.UserEndpoints;
import entity.Account;
import entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import service.AccountService;
import service.UserService;
import service.impl.AccountServiceMapImpl;
import service.impl.UserServiceMapImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Pavel Gordon
 */
public class Main {
    private static Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        UserEndpoints.init();
        AccountEndpoints.init();
        initData();
    }

    public static void initData() {
        UserService userService = UserServiceMapImpl.getInstance();
        AccountService accountService = AccountServiceMapImpl.getInstance();
        List<Account> accounts = new ArrayList<>(Arrays.asList(
                new Account("My first account for rubles", 1000, "RUB"),
                new Account("EUR savings for Europe trips", 500,"EUR")
        ));
        accountService.addAll(accounts);
        User user = new User("Pavel", "Pavlov", "Pavel.Pavlov@gmail.com", accounts);
        userService.addUser(user);
        user = new User("Petr", "Petrov", "Petr.Petrov@gmail.com");
        userService.addUser(user);
        user = new User("Ivan", "Ivan", "mk.neo@yandex.ru");
        userService.addUser(user);

        logger.info("Created 3 users");


    }
}
