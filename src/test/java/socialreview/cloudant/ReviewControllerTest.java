package socialreview.cloudant;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class ReviewControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getReviews() throws Exception {
        String endpoint = "http://localhost:" + port + "/micro/review";
        ResponseEntity<String> response = template.getForEntity(endpoint, String.class);
        assertThat(response.getStatusCode() , equalTo(HttpStatus.OK));
    }
}
