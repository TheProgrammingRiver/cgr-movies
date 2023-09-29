package definitions;

import com.example.cgrmovies.CgrMoviesApplication;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;


import java.util.logging.Logger;

@CucumberContextConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = CgrMoviesApplication.class)
public class UserControllerTestDefs {
    private static final String BASE_URL = "http://localhost:";
    private static final Logger logger = Logger.getLogger(UserControllerTestDefs.class.getName());

    @LocalServerPort
    private static String port;
    private static Response response;

    @When("A registered user logs in")
    public String aRegisteredUserLogsIn() throws JSONException {
        logger.info("Calling: A registered user logs in.");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "example@email.com");
        requestBody.put("password", "password");
        response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/login/");
        return response.jsonPath().getString("jwt");
    }

}
