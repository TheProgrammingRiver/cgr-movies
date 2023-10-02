package definitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MovieControllerTestDefs extends SetupTestDefs{

    private static final Logger log = Logger.getLogger(MovieControllerTestDefs.class.getName());
    static Response response;


    @Given("A list of movies are available")
    public void aListOfMoviesAreAvailable() {
        log.info("Calling A list of movies are available");
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("Authorization", "Bearer " + token);
            HttpEntity<String> entity = new HttpEntity<>(headers);

            ResponseEntity<String> response = new RestTemplate().exchange(BASE_URL + port + "/api/genres/1/movies", HttpMethod.GET, entity, String.class);
            List<Map<String, String>> movies = JsonPath.from(String.valueOf(response.getBody())).get("data");
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
            Assert.assertTrue(movies.size() > 0);

        } catch (HttpClientErrorException e) {
            e.printStackTrace();
        }
    }

    @When("I add a movie with a specific genre to my list")
    public void iAddAMovieWithASpecificGenreToMyList() throws JSONException {
        log.info("Calling I add a movie to my movies list");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "Movie Name");
        requestBody.put("description", "Movie Description");
        request.header("Content-Type", "application/json");
        request.headers("Authorization","Bearer " + token);
        response = request.body(requestBody.toString()).post(BASE_URL+port+"/api/genres/1/movies");
    }

    @Then("The movie is added")
    public void theMovieIsAdded() {
        log.info("Calling theMovieIsAdded");
        Assert.assertEquals(201, response.getStatusCode());
    }

    @When("I edit a movie from my movies list")
    public void iEditAMovieFromMyMoviesList() throws JSONException {
        log.info("Calling I edit a movie from my movies list");
        RestAssured.baseURI = BASE_URL;
        RequestSpecification request = RestAssured.given();
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", "A Movie Name");
        requestBody.put("description", "A Movie Description");
        request.header("Content-Type", "application/json");
        request.headers("Authorization","Bearer " + token);
        response = request.body(requestBody.toString()).put(BASE_URL+port+"/api/genres/1/movies/1/");
    }


    @When("I remove  a movie with a specific genre from my list")
    public void iRemoveAMovieWithASpecificGenreFromMyList() {
        log.info("Calling I remove  a movie with a specific genre from my list");
        RequestSpecification request = RestAssured.given();
        request.header("Content-Type", "application/json");
        request.headers("Authorization","Bearer " + token);
        response = request.delete(BASE_URL+port+"/api/genres/1/movies/1/");
    }

    @Then("The movie is removed")
    public void theMovieIsRemoved() {
        log.info("Calling theMovieIsRemoved");
        Assert.assertEquals(200, response.getStatusCode());
    }

}
