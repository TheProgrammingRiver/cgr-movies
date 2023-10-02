package definitions;

import com.example.cgrmovies.CgrMoviesApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CgrMoviesApplication.class)
public class SetupTestDefs {
    protected static final String BASE_URL = "http://localhost:";

    protected static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxlQGVtYWlsLmNvbSIsImlhdCI6MTY5NjI1Nzc5MSwiZXhwIjoxNjk2MzQ0MTkxfQ.8UivRr_y5fle8-NGeqPDpXCtouNG7uyuztAAqivC0YI";

    @LocalServerPort
    protected String port;
}
