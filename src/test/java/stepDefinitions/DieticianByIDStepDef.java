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
		    String resolvedEndpoint = endpoint.replace("{dieticianId}", DieticianCreatDetailsStepDef.dieticianId);
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

	@Given("Admin create POST request get by Id module")
	public void admin_create_post_request_get_by_id_module() {
		
		dieticianGetIdTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianGetIdTests(), "GetById_DT_002");
	    LoggerLoad.info("Loaded Dietician Test Case: " + dieticianGetIdTestCase.getScenario());

	    request = Hooks.request;
	}

	@When("Admin send POST http request with endpoint get by Id module")
	public void admin_send_post_http_request_with_endpoint_get_by_id_module() {
		
		String endpoint = dieticianGetIdTestCase.getEndpoints();
		
	    String resolvedEndpoint = endpoint.replace("{dieticianId}", DieticianCreatDetailsStepDef.dieticianId);
	    LoggerLoad.info("Resolved Endpoint: " + resolvedEndpoint);

	    response = Hooks.request.post(resolvedEndpoint);  //<-- Negative testcase post method
	    response.then().log().all();
	    
	}

	@Then("Admin recieves {int} method not allowed in get by Id module")
	public void admin_recieves_method_not_allowed_in_get_by_id_module(Integer expectedStatusCode) {
		
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	    assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	    assertTrue(response.getStatusLine().contains(dieticianGetIdTestCase.getExpectedStatusLineMsg()));

	}
	
	@Given("Admin create GET request get by Id module")
	public void admin_create_get_request_get_by_id_module() {
		
		dieticianGetIdTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianGetIdTests(), "GetById_DT_003");
	    LoggerLoad.info("Loaded Dietician Test Case: " + dieticianGetIdTestCase.getScenario());

	    request = Hooks.request;	
	 
	}

	@When("Admin send GET http request with endpoint get by Id module")
	public void admin_send_get_http_request_with_endpoint_get_by_id_module() {
		
        String endpoint = dieticianGetIdTestCase.getEndpoints();
		
	    //<-- Negative testcase invalid ID mentioned in json data
	    LoggerLoad.info("Resolved Endpoint: " + endpoint);

	    response = Hooks.request.get(endpoint);  
	    response.then().log().all();
	}

	@Then("Admin recieves {int} not found in get by Id module")
	public void admin_recieves_not_found_in_get_by_id_module(Integer expectedStatusCode) {
		
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	    assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	    assertTrue(response.getStatusLine().contains(dieticianGetIdTestCase.getExpectedStatusLineMsg()));
  
	}
	
	@Given("Admin create GET request get by Id dietician module")
	public void admin_create_get_request_get_by_id_dietician_module() {
		
		dieticianGetIdTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianGetIdTests(), "GetById_DT_004");
	    LoggerLoad.info("Loaded Dietician Test Case: " + dieticianGetIdTestCase.getScenario());

	    request = Hooks.request;
	   
	}

	@When("Admin send GET http request with endpoint get by Id dietician module")
	public void admin_send_get_http_request_with_endpoint_get_by_id_dietician_module() {
	   
		String endpoint = dieticianGetIdTestCase.getEndpoints();

	    response = Hooks.request.get(endpoint);  
	    response.then().log().all();
	}

}
