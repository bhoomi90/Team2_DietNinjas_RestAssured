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

	@When("Admin send PUT http request with endpoint in update dietician module")
	public void admin_send_put_http_request_with_endpoint_in_update_dietician_module() {
		String endpoint = updateDieticianTestCase.getEndpoints();

	    // Replace {dieticianId} with actual ID
	    String addedEndpoint = endpoint.replace("{dieticianId}", DieticianCreatDetailsStepDef.dieticianId);
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

	@Given("Admin creates PUT request only with valid mandatory details")
	public void admin_creates_put_request_only_with_valid_mandatory_details() {
	    
		// Load current dietician test data
				updateDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getUpdateDieticianTests(), "PutDT_002");
		        LoggerLoad.info("Loaded Dietician Test Case: " + updateDieticianTestCase.getScenario());

		        // Get input data
		        dieticianInputdata = updateDieticianTestCase.getDieticianInputdata();
		        LoggerLoad.info("Loaded Updated Dietician Test Case: " + dieticianInputdata);

		        Hooks.request = Hooks.request.body(dieticianInputdata);
		        Hooks.request.log().all();
		        LoggerLoad.info("Request with body prepared.");		
	}

	@Given("Admin creates PUT request only with valid additional details")
	public void admin_creates_put_request_only_with_valid_additional_details() {
		// Load current dietician test data
		updateDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getUpdateDieticianTests(), "PutDT_003");
        LoggerLoad.info("Loaded Dietician Test Case: " + updateDieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = updateDieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Updated Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
        LoggerLoad.info("Request with body prepared.");	
	}

	@Then("Admin recieves {int} Bad request in update Module")
	public void admin_recieves_bad_request_in_update_module(Integer expectedStatusCode) {
	  
		 response.prettyPrint();

	     assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(updateDieticianTestCase.getExpectedStatusLineMsg()));
	     
	}


	@Given("Admin creates PUT request only with invalid additional details")
	public void admin_creates_put_request_only_with_invalid_additional_details() {
	    
		// Load current dietician test data
				updateDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getUpdateDieticianTests(), "PutDT_004");
		        LoggerLoad.info("Loaded Dietician Test Case: " + updateDieticianTestCase.getScenario());

		        // Get input data
		        dieticianInputdata = updateDieticianTestCase.getDieticianInputdata();
		        LoggerLoad.info("Loaded Updated Dietician Test Case: " + dieticianInputdata);

		        Hooks.request = Hooks.request.body(dieticianInputdata);
		        Hooks.request.log().all();
		    
		
	}
	
	@Given("Admin creates PUT request only with valid mandatory details updateid")
	public void admin_creates_put_request_only_with_valid_mandatory_details_updateid() {
		// Load current dietician test data
		updateDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getUpdateDieticianTests(), "PutDT_005");
        LoggerLoad.info("Loaded Dietician Test Case: " + updateDieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = updateDieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Updated Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
    
	}
	@When("Admin send PUT http request with endpoint in update invalidid dietician module")
	public void admin_send_put_http_request_with_endpoint_in_update_invalidid_dietician_module() {
		
        String endpoint = updateDieticianTestCase.getEndpoints();
		
	    //<-- Negative testcase invalid ID mentioned in json data
	    LoggerLoad.info("Resolved Endpoint: " + endpoint);

	    response = Hooks.request.get(endpoint);  
	    response.then().log().all();
	}


	@Then("Admin recieves {int} not found in update Dietician Module")
	public void admin_recieves_not_found_in_update_dietician_module(Integer expectedStatusCode) {
	
	     assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(updateDieticianTestCase.getExpectedStatusLineMsg()));
	     
	}

	@Given("Admin creates POST request only with valid details in updateDietician Module")
	public void admin_creates_post_request_only_with_valid_details_in_update_dietician_module() {
	   
		// Load current dietician test data
		updateDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getUpdateDieticianTests(), "PutDT_006");
        LoggerLoad.info("Loaded Dietician Test Case: " + updateDieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = updateDieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Updated Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
	}
	
	@When("Admin send POST http request with endpoint in updateDietician Module")
	public void admin_send_post_http_request_with_endpoint_in_update_dietician_module() {

		String endpoint = updateDieticianTestCase.getEndpoints();
		
	    String resolvedEndpoint = endpoint.replace("{dieticianId}", DieticianCreatDetailsStepDef.dieticianId);
	    LoggerLoad.info("Resolved Endpoint: " + resolvedEndpoint);

	    response = Hooks.request.post(resolvedEndpoint);  //<-- Negative testcase post method
	    response.then().log().all();
	    
	}
	
	@Then("Admin recieves {int} method not allowed in updateDietician Module")
	public void admin_recieves_method_not_allowed_in_update_dietician_module(Integer expectedStatusCode) {
		
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(updateDieticianTestCase.getExpectedStatusLineMsg()));
	}
	
	@Given("Admin creates PUT request with valid data in update dietician module")
	public void admin_creates_put_request_with_valid_data_in_update_dietician_module() {
		
		 // Load current dietician test data
		updateDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getUpdateDieticianTests(), "PutDT_007");
        LoggerLoad.info("Loaded Dietician Test Case: " + updateDieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = updateDieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Updated Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();		   
	 
	}
	
	@When("Admin sent PUT http request with invalid endpoint")
	public void admin_sent_put_http_request_with_invalid_endpoint() {
		
		String endpoint = updateDieticianTestCase.getEndpoints();
   	    LoggerLoad.info("Endpoint: " + endpoint);
   	    response = Hooks.request.post(endpoint);          //<---negative testcase invalid endpoint in jsondata
   	
        LoggerLoad.info("Status Code: " + response.getStatusCode());
        LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
      
	    
	}
	
	@Given("Admin creates PUT request with valid data and invalid content type")
	public void admin_creates_put_request_with_valid_data_and_invalid_content_type() {
	    
		// Load current dietician test data
				updateDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getUpdateDieticianTests(), "PutDT_008");
		        LoggerLoad.info("Loaded Dietician Test Case: " + updateDieticianTestCase.getScenario());

		        // Get input data
		        dieticianInputdata = updateDieticianTestCase.getDieticianInputdata();
		        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

		        Hooks.request = Hooks.request.body(dieticianInputdata).header("Content-Type", "application/x-www-form-urlencoded");
		        Hooks.request.log().all();
		        LoggerLoad.info("Request with body prepared.");
		
	}

	
	@Then("Admin recieves {int} unsupported media type in update Dietician Module")
	public void admin_recieves_unsupported_media_type_in_update_dietician_module(Integer expectedStatusCode) {
		
		assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(updateDieticianTestCase.getExpectedStatusLineMsg()));
	    
	}


}
