package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.TestCaseData;
import pojo.dieticianData;
import utilities.JSONDataReader;
import utilities.LoggerLoad;

public class DieticianCreatDetailsStepDef {

    private RequestSpecification request;
    private Response response;
    TestCaseData dieticianTestCase;
    dieticianData dieticianInputdata;
    public static String dieticianId;       //<-- save id and loginpassword
    public static String dieticianLoginPwd; 
    public static String dieticianEmail;
  
		
		@Given("Set admin bearer token in Dietician Module")
		public void set_admin_bearer_token_in_dietician_module() {
	        // Use bearer token from login
	        String token = AdminLoginStepDef.authToken;
	        if (token == null || token.isEmpty()) {
	            throw new RuntimeException("Token not set from login. Make sure login scenario runs before this.");
	        }

	        // Prepare request with token
	        request = given()
	                .baseUri(Hooks.baseUrl)
	                .header("Authorization", "Bearer " + token)
	                .header("Content-Type", "application/json");

	        Hooks.request = request; // You should set it here so other step classes can use it
		  
		}
		
		@Given("Admin creates POST request with valid data in Dietician Module")
		public void admin_creates_post_request_with_valid_data_in_dieticianmodule() {
		   

	    	// Load current dietician test data
	    	dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_001");
	        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());

	        // Get input data
	        dieticianInputdata = dieticianTestCase.getDieticianInputdata();
	        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

	        Hooks.request = Hooks.request.body(dieticianInputdata);
	        Hooks.request.log().all();
	        LoggerLoad.info("Request with body prepared.");
		}
		
		@When("Admin send POST http request with endpoint Dietician Module")
		public void admin_send_post_http_request_with_endpoint_dietician_module() {
		  

	    	String endpoint = dieticianTestCase.getEndpoints();
	    	 LoggerLoad.info("Endpoint: " + endpoint);
	    	 response = Hooks.request.post(endpoint);
	    	
	        LoggerLoad.info("Status Code: " + response.getStatusCode());
	        LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
		}
		
		@Then("Admin recieves {int} created and with response body Dietician Module")
		public void admin_recieves_created_and_with_response_body_dietician_module(Integer expectedStatusCode) {
		    
			response.prettyPrint();

	        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

	        assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
	        assertTrue(response.getStatusLine().contains(dieticianTestCase.getExpectedStatusLineMsg()));

	        //Extract and save the id and password from response
	        if(expectedStatusCode == 201) {
	        	
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

    
    @Given("Admin creates POST request only with invalid additional details")
    public void admin_creates_post_request_only_with_invalid_additional_details() {
       
    	// Load current dietician test data
    	dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_002");
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = dieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
        LoggerLoad.info("Request with body prepared.");
    }
    
    @Then("Admin recieves {int} Bad request in CreateDietician")
	public void admin_recieves_bad_request_in_create_dietician(Integer expectedStatusCode) {
    	response.prettyPrint();

        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
        assertTrue(response.getStatusLine().contains(dieticianTestCase.getExpectedStatusLineMsg()));
		
	}

    @Given("Admin creates PUT request only with valid details")
    public void admin_creates_put_request_only_with_valid_details() {
       
    	// Load current dietician test data
    	dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_003");
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = dieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
        LoggerLoad.info("Request with body prepared.");

    	
    }
    
    @When("Admin send PUT http request with endpoint in create dietician module")
    public void admin_send_put_http_request_with_endpoint_in_create_dietician_module() {
      
    	 
    	String endpoint = dieticianTestCase.getEndpoints();
    	 LoggerLoad.info("Endpoint: " + endpoint);
    	 response = Hooks.request.put(endpoint);          //<---negative testcase method
    	
        LoggerLoad.info("Status Code: " + response.getStatusCode());
        LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
    	
    }

    @Then("Admin recieves {int} method not allowed in CreateDietician")
	public void admin_recieves_method_not_allowed_in_create_dietician(Integer expectedStatusCode) {
	    
    	response.prettyPrint();

        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
        assertTrue(response.getStatusLine().contains(dieticianTestCase.getExpectedStatusLineMsg()));
	}

    @Given("Admin creates POST request with valid data in dieticianModule")
    public void admin_creates_post_request_with_valid_data_in_dietician_module() {
    	
    	// Load current dietician test data
    	dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_004");
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = dieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
        LoggerLoad.info("Request with body prepared.");

    }

    
    @When("Admin sent POST http request with invalid endpoint")
    public void admin_sent_post_http_request_with_invalid_endpoint() {
    	
    	String endpoint = dieticianTestCase.getEndpoints();
   	    LoggerLoad.info("Endpoint: " + endpoint);
   	    response = Hooks.request.post(endpoint);          //<---negative testcase invalid endpoint in jsondata
   	
        LoggerLoad.info("Status Code: " + response.getStatusCode());
        LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
      
    }
    
    @Then("Admin recieves {int} not found in CreateDietician")
	public void admin_recieves_not_found_in_create_dietician(Integer expectedStatusCode) {
	    
    	response.prettyPrint();

        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
        assertTrue(response.getStatusLine().contains(dieticianTestCase.getExpectedStatusLineMsg()));
		
	}
	
	@Given("Admin creates POST request with valid data and invalid content type")
	public void admin_creates_post_request_with_valid_data_and_invalid_content_type() {
		
		// Load current dietician test data
    	dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_005");
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = dieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata).header("Content-Type", "application/x-www-form-urlencoded");
        Hooks.request.log().all();
        LoggerLoad.info("Request with body prepared.");

	 
	}
	
	
	@Then("Admin recieves {int} unsupported media type in CreateDietician")
	public void admin_recieves_unsupported_media_type_in_create_dietician(Integer expectedStatusCode) {
	  
		response.prettyPrint();

        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
        assertTrue(response.getStatusLine().contains(dieticianTestCase.getExpectedStatusLineMsg()));
		
	}
	
	@Given("Admin creates POST request only with valid additional details")
	public void admin_creates_post_request_only_with_valid_additional_details() {
	   
		// Load current dietician test data
    	dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_006");
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());

        // Get input data
        dieticianInputdata = dieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
        LoggerLoad.info("Request with body prepared.");
	}


}
    