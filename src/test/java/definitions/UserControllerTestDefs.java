package definitions;

import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;



import java.util.logging.Logger;

public class UserControllerTestDefs  extends SetupTestDefs{
    private final Logger logger = Logger.getLogger(UserControllerTestDefs.class.getName());

    @When("A registered user logs in")
    public void aRegisteredUserLogsIn() throws JSONException {
        logger.info("Calling: A registered user logs in.");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        JSONObject requestBody = new JSONObject();
        requestBody.put("emailAddress", "example@email.com");
        requestBody.put("password", "password");
        logger.severe(BASE_URL + port + "/api/auth/users/login");
        Response response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/auth/users/login");
        logger.info( response.asString());

    }

}
