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

	@Given("Admin create DELETE request in dietician Module")
	public void admin_create_delete_request_in_dietician_module() {
	
		// Load current dietician test data
		deleteDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDeleteDieticianIdTests(), "Delete_DT_001");
		LoggerLoad.info("Loaded Dietician Test Case: " + deleteDieticianTestCase.getScenario());
        
        request = Hooks.request;
	    LoggerLoad.info("Reusing request with Bearer Token from background setup.");
	}
	
	@When("Admin send DELETE http request with endpoint in dietician Module")
	public void admin_send_delete_http_request_with_endpoint_in_dietician_module() {
	    
		String endpoint = deleteDieticianTestCase.getEndpoints();

		   
	    String Endpoint = endpoint.replace("{dieticianId}", DieticianCreatDetailsStepDef.dieticianId);
	    LoggerLoad.info("delete Endpoint: " + Endpoint);

	    response = Hooks.request.delete(Endpoint); // endpoint + extracted dieticianId //delete request
	    response.then().log().all();
	    
	    LoggerLoad.info("Status Code: " + response.getStatusCode());
	}
	
	@Then("Admin recieves {int} ok with details of the dietician id Delete dietician Module")
	public void admin_recieves_ok_with_details_of_the_dietician_id_delete_dietician_module(Integer expectedStatusCode) {
	    
		 assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(deleteDieticianTestCase.getExpectedStatusLineMsg()));
		
	}
	
	@Given("Admin create POST request in dietician Module")
	public void admin_create_post_request_in_dietician_module() {
	   

		// Load current dietician test data
		deleteDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDeleteDieticianIdTests(), "Delete_DT_002");
		LoggerLoad.info("Loaded Dietician Test Case: " + deleteDieticianTestCase.getScenario());
		        
	   request = Hooks.request;
        
	}
	
	@When("Admin send POST http request with endpoint in dietician Module")
	public void admin_send_post_http_request_with_endpoint_in_dietician_module() {
		
		String endpoint = deleteDieticianTestCase.getEndpoints();
		
	    String resolvedEndpoint = endpoint.replace("{dieticianId}", DieticianCreatDetailsStepDef.dieticianId);
	    LoggerLoad.info("Resolved Endpoint: " + resolvedEndpoint);

	    response = Hooks.request.post(resolvedEndpoint);  //<-- Negative testcase post method
	    response.then().log().all();
	   
	}
	
	@Then("Admin recieves {int} method not allowed id Delete dietician Module")
	public void admin_recieves_method_not_allowed_id_delete_dietician_module(Integer expectedStatusCode) {
	    
		 assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(deleteDieticianTestCase.getExpectedStatusLineMsg()));
		
	}
	
	@Given("Admin create DELETE request in dietician Module by invalid id")
	public void admin_create_delete_request_in_dietician_module_by_invalid_id() {
	  

		// Load current dietician test data
		deleteDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDeleteDieticianIdTests(), "Delete_DT_003");
		LoggerLoad.info("Loaded Dietician Test Case: " + deleteDieticianTestCase.getScenario());
        
        request = Hooks.request;
	    LoggerLoad.info("Reusing request with Bearer Token from background setup.");
	}

	@When("Admin send DELETE http request with endpoint in dietician Module by invalid id")
	public void admin_send_delete_http_request_with_endpoint_in_dietician_module_by_invalid_id() {
	    
		String endpoint = deleteDieticianTestCase.getEndpoints();
		
	    //<-- Negative testcase invalid ID mentioned in json data
	    LoggerLoad.info("Resolved Endpoint: " + endpoint);

	    response = Hooks.request.get(endpoint);  
	    response.then().log().all();
	}


	@Then("Admin recieves {int} not found id Delete dietician Module")
	public void admin_recieves_not_found_id_delete_dietician_module(Integer expectedStatusCode) {
	  
		 assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");
	     assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	     assertTrue(response.getStatusLine().contains(deleteDieticianTestCase.getExpectedStatusLineMsg()));
		
	}
	
	@Given("Admin create DELETE request in dietician Module with invalid endpoint")
	public void admin_create_delete_request_in_dietician_module_with_invalid_endpoint() {
		

		// Load current dietician test data
		deleteDieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDeleteDieticianIdTests(), "Delete_DT_004");
		LoggerLoad.info("Loaded Dietician Test Case: " + deleteDieticianTestCase.getScenario());
        
        request = Hooks.request;
	    LoggerLoad.info("Reusing request with Bearer Token from background setup.");
	  
	}
	
	@When("Admin send DELETE http request with invalid endpoint in dietician Module")
	public void admin_send_delete_http_request_with_invalid_endpoint_in_dietician_module() {
	
		String endpoint = deleteDieticianTestCase.getEndpoints();
   	    LoggerLoad.info("Endpoint: " + endpoint);
   	    response = Hooks.request.post(endpoint);          //<---negative testcase invalid endpoint in jsondata
   	
        LoggerLoad.info("Status Code: " + response.getStatusCode());
        LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
      
	}

	

}
