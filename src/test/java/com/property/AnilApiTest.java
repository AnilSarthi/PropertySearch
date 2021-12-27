package com.property;

import com.jayway.restassured.builder.RequestSpecBuilder;
import com.jayway.restassured.response.Response;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static com.jayway.restassured.RestAssured.given;

public class AnilApiTest {

@Given("I've berry endpoint")
    public void iHaveBerryEndpoint(){
    RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
    Response response = given().get("https://api.openbrewerydb.org/breweries");

}

    @Given("I've berries endpoint")
    public void iVeBerriesEndpoint() {
        
    }

    @When("I get berries")
    public void iGetBerries() {
        
    }

    @Then("I see berries")
    public void iSeeBerries() {
    }
}
