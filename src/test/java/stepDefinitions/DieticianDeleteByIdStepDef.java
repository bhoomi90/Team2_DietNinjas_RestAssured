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

public class DieticianDeleteByIdStepDef {
	
	 private RequestSpecification request;
	 private Response response;
	 TestCaseData deleteDieticianTestCase;
	 dieticianData dieticianInputdata;
	
	@Given("Admin create DELETE request")
	public void admin_create_delete_request() {
		
		// Load current dietician test data
		deleteDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDeleteDieticianIdTests(), "Delete_DT_001");
		LoggerLoad.info("Loaded Dietician Test Case: " + deleteDieticianTestCase.getScenario());
        
        request = Hooks.request;
	    LoggerLoad.info("Reusing request with Bearer Token from background setup.");

	}
	
	@When("Admin send DELETE http request with endpoint")
	public void admin_send_delete_http_request_with_endpoint() {
		
		
		String endpoint = deleteDieticianTestCase.getEndpoints();

	   
	    String Endpoint = endpoint.replace("{dieticianId}", DieticianStepDef.dieticianId);
	    LoggerLoad.info("delete Endpoint: " + Endpoint);

	    response = Hooks.request.delete(Endpoint); // endpoint + extracted dieticianId //delete request
	    response.then().log().all();
	    
	    LoggerLoad.info("Status Code: " + response.getStatusCode());
	    
	}
	
	@Then("Admin recieves {int} ok with details of the dietician id Delete")
	public void admin_recieves_ok_with_details_of_the_dietician_id_delete(Integer expectedStatusCode) {
	 
	     assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(deleteDieticianTestCase.getExpectedStatusLineMsg()));
	 }
	

}
