package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class GenreControllerTestDefs extends SetupTestDefs{
    private static final Logger log = Logger.getLogger(GenreControllerTestDefs.class.getName());

    private static Response response;
    private static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxlQGVtYWlsLmNvbSIsImlhdCI6MTY5NjE4MzQ2NSwiZXhwIjoxNjk2MjY5ODY1fQ.WP40iW9Cul4JRD0lbYmGc3TdKP3YzW6l8GypWXoL4Tg";

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


    @When("I view the list of genres")
    public void iViewTheListOfGenres() {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
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


    @Then("I should see the genre list")
    public void iShouldSeeTheGenreList() {
        log.info("Calling: I should see the genre list");
       Assert.assertEquals(200, response.getStatusCode());
    }
}
