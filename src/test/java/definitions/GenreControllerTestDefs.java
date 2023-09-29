package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;


public class GenreControllerTestDefs extends SetupTestDefs{
    private static final Logger log = Logger.getLogger(GenreControllerTestDefs.class.getName());

    private static Response response;

    @Given("A list of genres are available")
    public void aListOfGenresAreAvailable() {
        log.info("Calling: A list of genres are available");
        try {
            ResponseEntity<String> responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/genres/",
                    HttpMethod.GET, null, String.class);
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
            ResponseEntity<String> responseEntity = new RestTemplate().exchange(BASE_URL + port + "/api/genres/",
                    HttpMethod.GET, null, String.class);
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
