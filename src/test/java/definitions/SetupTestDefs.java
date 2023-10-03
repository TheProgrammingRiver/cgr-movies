package definitions;

import com.example.cgrmovies.CgrMoviesApplication;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CgrMoviesApplication.class)
public class SetupTestDefs {
    protected static final String BASE_URL = "http://localhost:";

    protected static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxlQGVtYWlsLmNvbSIsImlhdCI6MTY5NjM1NDIwOSwiZXhwIjoxNjk2NDQwNjA5fQ.SgNMr-YDlV1tCeJ-Oax4TT1darNHv9bxfPCp3-Qiemg";

    @LocalServerPort
    protected String port;
}
