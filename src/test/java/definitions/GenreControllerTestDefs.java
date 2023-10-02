package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class GenreControllerTestDefs extends SetupTestDefs{
    private static final Logger log = Logger.getLogger(GenreControllerTestDefs.class.getName());

    static Response response;

    @Given("A list of genres are available")
    public void aListOfGenresAreAvailable() {
        log.info("Calling: A list of genres are available");
        try {
            // Create HttpHeaders instance
            HttpHeaders headers = new HttpHeaders();
            // Set the authorization header, with a token
            headers.set("Authorization", "Bearer " + token);
            // Create HttpEntity with headers
            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/genres/",
                    HttpMethod.GET, entity, String.class);
            List<Map<String, String>> genreList = JsonPath.from(String.valueOf(responseEntity.getBody())).get("data");
            Assert.assertEquals(responseEntity.getStatusCode(), HttpStatus.OK);
            Assert.assertTrue(genreList.size() > 0);
        } catch (HttpClientErrorException e){
            e.printStackTrace();
        }
    }

    @When("I add a genre")
    public void iAddAGenre() {
       log.info("Calling: I add a genre");
       RestAssured.baseURI = BASE_URL;
       RequestSpecification request = RestAssured.given();
       JSONObject requestBody = new JSONObject();
       requestBody.put("name", "NewGenre");
       requestBody.put("description", "New Genre Description");
       request.header("Content-Type", "application/json");
       request.headers("Authorization", "Bearer " + token);
       response = request.body(requestBody.toString()).post(BASE_URL + port + "/api/genres/");
       }


    @Then("The genre is added")
    public void theGenreIsAdded() {
        log .info("Calling: The genre is added");
        Assert.assertEquals(201, response.getStatusCode());
    }
}
