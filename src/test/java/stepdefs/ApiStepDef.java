package stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ApiStepDef {

    private RequestSpecification requestSpecification;
    private Response response;

    @Given("^I am an api user$")
    public void iAmAnApiUser() {
        requestSpecification = given().body("");
    }

    @When("^I send a request$")
    public void iSendARequest() {
        response = requestSpecification.when().post("");
    }

    @Then("^I should get a response$")
    public void iShouldGetAResponse() {
        response.then();
    }
}
