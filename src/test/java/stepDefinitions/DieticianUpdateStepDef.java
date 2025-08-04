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

public class DieticianUpdateStepDef {
	
	 private RequestSpecification request;
	    private Response response;
	    TestCaseData updateDieticianTestCase;
	    dieticianData dieticianInputdata;
	    public static String dieticianId;       //<-- save id and loginpassword
	    public static String dieticianLoginPwd; 
	    public static String dieticianEmail;
	

	@Given("Admin creates PUT request with valid data. \\( Mandatory and additional details)")
	public void admin_creates_put_request_with_valid_data_mandatory_and_additional_details() {
	
		// Load current dietician test data
		updateDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getUpdateDieticianTests(), "PutDT_001");
        LoggerLoad.info("Loaded Dietician Test Case: " + updateDieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = updateDieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Updated Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
        LoggerLoad.info("Request with body prepared.");
	}

	@When("Admin send PUT http request with endpoint")
	public void admin_send_put_http_request_with_endpoint() {


		 String endpoint = updateDieticianTestCase.getEndpoints();

		    // Replace {dieticianId} with actual ID
		    String addedEndpoint = endpoint.replace("{dieticianId}", DieticianStepDef.dieticianId);
		    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);

		    response = Hooks.request.put(addedEndpoint); // endpoint + extracted dieticianId //put request
		    response.then().log().all();
		    
		    LoggerLoad.info("Status Code: " + response.getStatusCode());
	  
	}

	@Then("Admin recieves {int} ok and with updated response body.")
	public void admin_recieves_ok_and_with_updated_response_body(Integer expectedStatusCode) {
		
		 response.prettyPrint();

	     assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(updateDieticianTestCase.getExpectedStatusLineMsg()));
	     
	   //Extract and save the id and password from response
	        if(expectedStatusCode == 200) {
	        	
	        	dieticianId = response.jsonPath().getString("id");
	        	System.out.print("Dietician_Id saved:"+ dieticianId);
	        	LoggerLoad.info("Dietician_Id Password saved:"+ dieticianId);
	        	
	        	dieticianLoginPwd= response.jsonPath().getString("loginPassword");
	        	System.out.print("Dietician login Password saved:"+ dieticianLoginPwd);
	        	LoggerLoad.info("Dietician login Password saved:"+ dieticianLoginPwd);
	        	
	        	dieticianEmail = response.jsonPath().getString("Email");
	        	System.out.print("Dietician_Id saved:"+ dieticianEmail);
	        	LoggerLoad.info("Dietician_Id Password saved:"+ dieticianEmail);
	        	
	        }
	 
	}


}
