package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pojo.TestCaseData;
import pojo.dieticianData;
import utilities.JSONDataReader;
import utilities.LoggerLoad;
import utilities.apiTextContext;

public class DieticianCreatationforLoginStepDef {
	
	private TestCaseData dieticianTestCase;
	private dieticianData dieticianLoginInputdata;
	
	@Given("Admin creates POST request with valid dietician data")
	public void admin_creates_post_request_with_valid_dietician_data() {
		
		  dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianLoginTests(), "DT_login_001");
	        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());
	        
	        // Get input data
	        dieticianLoginInputdata = dieticianTestCase.getDieticianLoginInputdata();
	        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianLoginInputdata);

	        Hooks.request = Hooks.request.body(dieticianLoginInputdata);
	        Hooks.request.log().all();
	        LoggerLoad.info("Request with body prepared.");
	        apiTextContext.request = apiTextContext.request.body(dieticianLoginInputdata);
	        apiTextContext.request.log().all();
	        LoggerLoad.info("Loaded request:" +  apiTextContext.request);
	   
	}
	@When("Admin send POST http request with dietician endpoint")
	public void admin_send_post_http_request_with_dietician_endpoint() {
		
		String endpoint = dieticianTestCase.getEndpoints();
   	 LoggerLoad.info("Endpoint: " + endpoint);
   	 apiTextContext.response = Hooks.request.post(endpoint);
   	
       LoggerLoad.info("Status Code: " + apiTextContext.response.getStatusCode());
       LoggerLoad.info("Response Body: " + apiTextContext.response.getBody().asPrettyString());
	   
	}
	@Then("Admin recieves {int} created and with dietician response body")
	public void admin_recieves_created_and_with_dietician_response_body(Integer expectedStatusCode) {
		
		 assertEquals(	apiTextContext.response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

	        assertTrue(	apiTextContext.response.getStatusLine().contains(expectedStatusCode.toString()));
	        assertTrue(	apiTextContext.response.getStatusLine().contains(dieticianTestCase.getExpectedStatusLineMsg()));

	        //Extract and save the id and password from response
	        if(expectedStatusCode == 201) {
	        	
	        	apiTextContext.dieticianid = 	apiTextContext.response.jsonPath().getString("id");
	        
	        	LoggerLoad.info("Dietician_Id Password saved:"+ apiTextContext.dieticianid);
	        	
	        	apiTextContext.dieticianPassword= 	apiTextContext.response.jsonPath().getString("loginPassword");
	        	LoggerLoad.info("Dietician login Password saved:"+ apiTextContext.dieticianPassword);
	        	
	        	apiTextContext.dieticianLoginEmail = apiTextContext.response.jsonPath().getString("Email");
	        	LoggerLoad.info("Dietician_Id Password saved:"+ apiTextContext.dieticianLoginEmail);
	        	
	        }
	   
	}

}
