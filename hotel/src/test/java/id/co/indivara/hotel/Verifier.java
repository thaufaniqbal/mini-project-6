package id.co.indivara.hotel;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class Verifier {
    @Value("${server.port}")
    private String port;

    @BeforeEach
    public void setup() {
        RestAssured.port = Integer.parseInt(port);
    }
}
