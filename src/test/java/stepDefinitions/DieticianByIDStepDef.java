package stepDefinitions;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.TestCaseData;
import pojo.dieticianData;
import utilities.JSONDataReader;
import utilities.LoggerLoad;

public class DieticianByIDStepDef {
	
	private RequestSpecification request;
	private Response response;
	TestCaseData dieticianGetIdTestCase;
	dieticianData dieticianInputdata;
	 public static String dieticianId;  
	@Given("Admin create GET request for dietician by ID")
	public void admin_create_get_request_for_dietician_by_id() {
		
		dieticianGetIdTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianGetIdTests(), "GetById_DT_001");
	    LoggerLoad.info("Loaded Dietician Test Case: " + dieticianGetIdTestCase.getScenario());

	    request = Hooks.request;
	    LoggerLoad.info("Reusing request with Bearer Token from background setup.");

	}

	
	@When("Admin send GET http request with endpoint and dietician ID")
	public void admin_send_get_http_request_with_endpoint_and_dietician_id() {
		
		
		 String endpoint = dieticianGetIdTestCase.getEndpoints();

		    // Replace {dieticianId} with actual ID
		    String resolvedEndpoint = endpoint.replace("{dieticianId}", DieticianStepDef.dieticianId);
		    LoggerLoad.info("Resolved Endpoint: " + resolvedEndpoint);

		    response = Hooks.request.get(resolvedEndpoint); // Use resolved endpoint
		    response.then().log().all();
		    
		    LoggerLoad.info("Status Code: " + response.getStatusCode());
	}

	@Then("Admin recieves {int} ok with details of the dietician id")
	public void admin_recieves_ok_with_details_of_the_dietician_id(Integer expectedStatusCode) {
		
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	    assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	    assertTrue(response.getStatusLine().contains(dieticianGetIdTestCase.getExpectedStatusLineMsg()));

	    
	}


}
