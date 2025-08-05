package stepDefinitions;

import static io.restassured.RestAssured.given;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.JSONDataReader;
import utilities.LoggerLoad;
import utilities.apiTextContext;
import utilities.configReader;

public class GetAllPatientStepDef {
	
	private String token;
	
	@Given("Admin create GET request for get all patient")
	public void admin_create_get_request_for_get_all_patient() {
		
		 Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getGetAllPatientsTests(), "GPT_001");
		 LoggerLoad.info("Loaded get all patients Test Case: " + Hooks.currentLoginTest.getScenario());
	    
	}
	@When("Admin send GET http request with endpoint for get all patient")
	public void admin_send_get_http_request_with_endpoint_for_get_all_patient() {
		
		
		apiTextContext.response =apiTextContext.request. when().get(Hooks.currentLoginTest.getEndpoints());
		LoggerLoad.info("response from get all patients"+ apiTextContext.response.asPrettyString());
	    
	}
	@Then("Admin recieves {int} Forbidden")
	public void admin_recieves_forbidden(Integer expectedStatusCode) {
	   
		apiTextContext.response.then().statusCode(expectedStatusCode)
		.statusLine("HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
		.log().all();
	}
	@Given("Set patient token")
	public void set_patient_token() {
		
        token = apiTextContext.patientToken;
		LoggerLoad.info("Patient token value : " + token);
		apiTextContext.request = given().baseUri(Hooks.baseUrl)
				                .header("Authorization", "Bearer " + token)
                                .header("Content-Type", "application/json");
	}
	
	@Given("Patient create GET request")
	public void patient_create_get_request() {
		
		 Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getGetAllPatientsTests(), "GPT_002");
		 LoggerLoad.info("Loaded get all patients Test Case: " + Hooks.currentLoginTest.getScenario());
	    
	}
	@When("Patient send GET http request with endpoint")
	public void patient_send_get_http_request_with_endpoint() {
		
		apiTextContext.response =apiTextContext.request. when().get(Hooks.currentLoginTest.getEndpoints());
		LoggerLoad.info("response from get all patients"+ apiTextContext.response.asPrettyString());
	   
	}
	@Then("Patient recieves {int} Forbidden")
	public void patient_recieves_forbidden(Integer expectedStatusCode) {
		
		apiTextContext.response.then().statusCode(expectedStatusCode)
		.statusLine("HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
		.log().all();
	    
	}
	
	
	@Given("Set dietician token")
	public void set_dietician_token() {
		
		
		token = apiTextContext.dieticianToken;
		LoggerLoad.info("Dietician token value : " + token);
		apiTextContext.request = given().baseUri(Hooks.baseUrl).header("Authorization", "Bearer " + token)
				.header("Content-Type", "application/json");
	}
	@Given("Dietician create GET request")
	public void dietician_create_get_request() {
		
		 Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getGetAllPatientsTests(), "GPT_003");
		 LoggerLoad.info("Loaded get all patients Test Case: " + Hooks.currentLoginTest.getScenario());
	   
	}
	@When("Dietician send GET http request with endpoint")
	public void dietician_send_get_http_request_with_endpoint() {
	   
		apiTextContext.response =apiTextContext.request. when().get(Hooks.currentLoginTest.getEndpoints());
		LoggerLoad.info("response from get all patients"+ apiTextContext.response.asPrettyString());
	}
	@Then("Dietician recieves {int} ok with response body")
	public void dietician_recieves_ok_with_response_body(Integer expectedStatusCode) {

		apiTextContext.response.then().statusCode(expectedStatusCode)
		.statusLine("HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
		.log().all();
	   
	}
	
	@Given("Dietician create PUT request")
	public void dietician_create_put_request() {
		
		 Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getGetAllPatientsTests(), "GPT_004");
		 LoggerLoad.info("Loaded get all patients Test Case: " + Hooks.currentLoginTest.getScenario());
	    
	}
	@When("Dietician send PUT http request with endpoint")
	public void dietician_send_put_http_request_with_endpoint() {
		
		apiTextContext.response =apiTextContext.request. when().put(Hooks.currentLoginTest.getEndpoints());
		LoggerLoad.info("response from get all patients"+ apiTextContext.response.asPrettyString());
	    
	}
	@Then("Dietician recieves {int} method not allowed")
	public void dietician_recieves_method_not_allowed(Integer expectedStatusCode) {
	   LoggerLoad.info("Validate Response with invalid method");
		apiTextContext.response.then().statusCode(expectedStatusCode)
		.statusLine("HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
		.log().all();
	}
	
	@Given("Dietician create GET request with invalid endpoint")
	public void dietician_create_get_request_with_invalid_endpoint() {
	    
		 Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getGetAllPatientsTests(), "GPT_005");
		 LoggerLoad.info("Loaded get all patients Test Case: " + Hooks.currentLoginTest.getScenario());
	    
	}
	
	@When("Dietician send GET http request with invalid endpoint")
	public void dietician_send_get_http_request_with_invalid_endpoint() {
		apiTextContext.response =apiTextContext.request. when().put(Hooks.currentLoginTest.getEndpoints());
		LoggerLoad.info("response from get all patients"+ apiTextContext.response.asPrettyString());
	}
	@Then("Dietician recieves {int} not found")
	public void dietician_recieves_not_found(Integer expectedStatusCode) {
	    
		 LoggerLoad.info("Validate Response with invalid method");
			apiTextContext.response.then().statusCode(expectedStatusCode)
			.statusLine("HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
			.log().all();
	}
	
	@Given("Dietician create GET request with no auth")
	public void dietician_create_get_request_with_no_auth() {
	   
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getGetAllPatientsTests(), "GPT_006");
		 LoggerLoad.info("Loaded get all patients Test Case: " + Hooks.currentLoginTest.getScenario());
		 apiTextContext.request = given().baseUri(Hooks.baseUrl)
                 .header("Content-Type", "application/json");
	}
	@When("Dietician send GET http request with endpoint with no auth")
	public void dietician_send_get_http_request_with_endpoint_with_no_auth() {
		
		apiTextContext.response =apiTextContext.request. when().put(Hooks.currentLoginTest.getEndpoints());
		LoggerLoad.info("response from get all patients"+ apiTextContext.response.asPrettyString());
	}
	@Then("Dietician recieves {int} unauthorized")
	public void dietician_recieves_unauthorized(Integer expectedStatusCode) {
		 LoggerLoad.info("Validate Response with invalid method");
			apiTextContext.response.then().statusCode(expectedStatusCode)
			.statusLine("HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
			.log().all();
	}



}
