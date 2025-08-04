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

public class DieticianGetAllStepDef {
	
	private RequestSpecification request;
	private Response response;
	TestCaseData dieticianGETAllTestCase;
	dieticianData dieticianInputdata;
	
	@Given("Admin create GET request")
	public void admin_create_get_request() {
	 
		dieticianGETAllTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianGetallTests(), "GetAllDT_001");
	    LoggerLoad.info("Loaded Dietician Test Case: " + dieticianGETAllTestCase.getScenario());

	   //just reuse the one set in background
	    request = Hooks.request;
	    LoggerLoad.info("Reusing request with Bearer Token from background setup.");
                       
	}
	
	@When("Admin send GET http request with endpoint")
	public void admin_send_get_http_request_with_endpoint() {
		
		String endpoint = dieticianGETAllTestCase.getEndpoints();
   	    LoggerLoad.info("Endpoint: " + endpoint);
   	    response = Hooks.request.get(endpoint);   // <-- Get request
   	    response.then().log().all();
   	    
        LoggerLoad.info("Status Code: " + response.getStatusCode());
      //  LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
   	
	}
	
	@Then("Admin recieves {int} ok with response body")
	public void admin_recieves_ok_with_response_body(Integer expectedStatusCode) {
	   
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	    assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	    assertTrue(response.getStatusLine().contains(dieticianGETAllTestCase.getExpectedStatusLineMsg()));

    }
}
