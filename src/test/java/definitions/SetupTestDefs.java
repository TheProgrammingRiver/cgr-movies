package definitions;

import com.example.cgrmovies.CgrMoviesApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CgrMoviesApplication.class)
public class SetupTestDefs {
    protected static final String BASE_URL = "http://localhost:";

    @LocalServerPort
    protected String port;
}
