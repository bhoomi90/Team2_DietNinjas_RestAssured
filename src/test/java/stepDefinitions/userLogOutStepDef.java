package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.JSONDataReader;
import utilities.apiTextContext;

import static io.restassured.RestAssured.*;

public class userLogOutStepDef {
	
	private String token;
	
	@Given("Set bearer token in header")
	public void set_bearer_token_in_header() {
	  
		token = apiTextContext.authToken;
		
		apiTextContext.request = given().baseUri(Hooks.baseUrl)
				                .header("Authorization", "Bearer " + token)
                                .header("Content-Type", "application/json");
		
		//apiTextContext.requestSpec = builder.build();
		
	}
	
	@Given("User creates GET request for logout")
	public void user_creates_get_request_for_logout() {
		 Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLogoutTests(), "LOT_004");
		   
	}
	@When("User send GET HTTP request with valid endpoint")
	public void user_send_get_http_request_with_valid_endpoint() {
		apiTextContext.response =apiTextContext.request. when().get(Hooks.currentLoginTest.getEndpoints());
	}
	@Then("User recieves {int} created with Logout successful message")
	public void user_recieves_created_with_logout_successful_message(Integer expectedStatusCode) {
	  
		apiTextContext.response.then().statusCode(expectedStatusCode)
		.statusLine("HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
		.log().all();
	}
	@Given("User creates POST request")
	public void user_creates_post_request() {
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLogoutTests(), "LOT_005");

	    apiTextContext.request = given()
	        .baseUri(Hooks.baseUrl)
	        .header("Authorization", "Bearer " + apiTextContext.authToken) // assuming token is shared
	        .header("Content-Type", "application/json");
	}

}
