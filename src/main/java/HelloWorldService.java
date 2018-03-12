
import static spark.Spark.*;

/**
 * @author Pavel Gordon
 */
public class HelloWorldService {
    public static void main(String[] args) {
        get("/hello", (req, res) -> "Hello World");
    }
}