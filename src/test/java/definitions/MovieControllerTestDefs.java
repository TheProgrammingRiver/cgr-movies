package definitions;

import io.cucumber.java.en.Given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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

    private static final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJleGFtcGxlQGVtYWlsLmNvbSIsImlhdCI6MTY5NjE4MzQ2NSwiZXhwIjoxNjk2MjY5ODY1fQ.WP40iW9Cul4JRD0lbYmGc3TdKP3YzW6l8GypWXoL4Tg";

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


}
