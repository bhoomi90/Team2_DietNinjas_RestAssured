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
import utilities.apiTextContext;

public class DieticianStepDef {

    private RequestSpecification request;
    private Response response;
    TestCaseData dieticianTestCase;
    dieticianData dieticianInputdata;
    public static String dieticianId;       //<-- save id and loginpassword
    public static String dieticianLoginPwd; 
    public static String dieticianEmail;
   
    @Given("Set admin bearer token")
    public void set_admin_bearer_token() {
        // Use bearer token from login

        String token = apiTextContext.authToken; 
    	//String token = UserLoginStepDef.authToken;
        LoggerLoad.info("Admin token value : " + token);
        
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

    @Given("Admin creates POST request with valid data")
    public void admin_creates_post_request_with_valid_data() {
       
    	// Load current dietician test data

        dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_001");
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());
        
        // Get input data
        dieticianInputdata = dieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

        Hooks.request = Hooks.request.body(dieticianInputdata);
        Hooks.request.log().all();
        LoggerLoad.info("Request with body prepared.");
        request = request.body(dieticianInputdata);
        request.log().all();
        LoggerLoad.info("Loaded request:" + request);
    }

    @When("Admin send POST http request with endpoint")
    public void admin_send_post_http_request_with_endpoint() {
      
       
    	String endpoint = dieticianTestCase.getEndpoints();
    	 LoggerLoad.info("Endpoint: " + endpoint);
    	 response = Hooks.request.post(endpoint);
    	  //response= request.post(endpoint);
    	
        LoggerLoad.info("Status Code: " + response.getStatusCode());
        LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
    }

    @Then("Admin recieves {int} created and with response body")
    public void admin_recieves_created_and_with_response_body(Integer expectedStatusCode) {
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
}