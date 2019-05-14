package config;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.parsing.Parser;
import org.testng.annotations.BeforeTest;

import static org.hamcrest.Matchers.lessThanOrEqualTo;

public class TestConfig {

    @BeforeTest (
            description = "This initializes the request and response specs and applies it to all your tests (global specs)"
    )
    public void setup() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .setBaseUri("https://restful-booker.herokuapp.com")
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectResponseTime(lessThanOrEqualTo(5000L))
                .build();

        // Just makes sure that when we get a response whose format is 'text/plain'
        // we parse it into JSON to make it easy for us to query
        RestAssured.registerParser("text/plain", Parser.JSON);
    }
}
