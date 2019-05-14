package tests;

import config.TestConfig;
import io.restassured.http.ContentType;
import org.testng.annotations.*;
import utils.Endpoint;
import utils.Resources;

import java.io.File;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

public class RestfulBookerTest extends TestConfig {

    private String accessToken;
    private String bookingId;

    @Test (
            priority = 1,
            description = "Get all booking IDs"
    )
    public void getAllBookingIds() {
        given()
        .when()
                .get(Endpoint.BOOKING)
        .then()
                .contentType(ContentType.JSON)
                .statusCode(200);
    }

    @Test (
            priority = 2,
            description = "Get access token and store in the 'accessToken' variable"
    )
    public void getAccessToken() {

        accessToken =

            given()
                    .body(new File(Resources.PAYLOAD + "credentials.json"))
            .when()
                    .post(Endpoint.AUTH)
            .then()
                    .contentType(ContentType.JSON)
                    .statusCode(200)
            .and()
                    .extract()
                    .path("token").toString();
    }

    @Test (
            priority = 3,
            description = "Creates a new booking and stores the new booking ID in the 'bookingId' variable"
    )
    public void createBooking() {

        bookingId =

        given()
                .body(new File(Resources.PAYLOAD + "create_booking.json"))
        .when()
                .post(Endpoint.BOOKING)
        .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
        .and()
                .extract()
                .path("bookingid")
                .toString();
    }

    @Test (
            priority = 4,
            description = "Get the newly created booking by using the bookingId as path parameter" +
                    "then check the response body against a schema to check that the JSON response is" +
                    "structured well."
    )
    public void getNewlyAddedBooking() {
        given()
                .pathParam("id", bookingId)
        .when()
                .get(Endpoint.BOOKING_ID)
        .then()
                .contentType(ContentType.JSON)
                .statusCode(200)
                .body(matchesJsonSchema(new File(Resources.SCHEMA + "get_booking_id_schema.json")))
                .log().all();

        /*
        * The JSON schema can be generated from https://jsonschema.net. Just paste in the expected JSON response
        * and a schema will be generated for you. Then create a JSON file and paste in the schema into it then save
        * the file inside the resources/schema directory. Now every time you get a JSON response,
        * you can check it's structure against the schema you created to check if it conformed to the desired JSON schema
        * */


    }

    @Test (
            priority = 5,
            description = "Delete the created booking by using the bookingId as path parameter. " +
                    "You need the access token to be able to perform DELETE"
    )
    public void deleteBooking() {
        given()
                .header("Cookie", "token=" + accessToken)
                .pathParam("id", bookingId)
        .when()
                .delete(Endpoint.BOOKING_ID)
        .then()
                .statusCode(201)
                .log().all();
    }
}
