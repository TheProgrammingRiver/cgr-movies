package definitions;

import com.example.cgrmovies.CgrMoviesApplication;
import io.cucumber.java.en.Given;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.response.Response;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.logging.Logger;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CgrMoviesApplication.class)
public class UserControllerTestDefs {
    private static final String BASE_URL = "http://localhost:";
    private static final Logger logger = Logger.getLogger(UserControllerTestDefs.class.getName());

    @LocalServerPort
    String port;
    private static Response response;

    @Given("A user")
    public void aUser() {

    }
}
