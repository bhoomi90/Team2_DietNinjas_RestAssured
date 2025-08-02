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

public class DieticianStepDef {

    private RequestSpecification request;
    private Response response;
    TestCaseData dieticianTestCase;
    dieticianData dieticianInputdata;
   
    @Given("Set admin bearer token")
    public void set_admin_bearer_token() {
        // Use bearer token from login
        String token = UserLoginStepDef.authToken;
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token not set from login. Make sure login scenario runs before this.");
        }

        // Prepare request with token
        request = given()
                .baseUri(Hooks.baseUrl)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
        		
    }

    @Given("Admin creates POST request with valid data")
    public void admin_creates_post_request_with_valid_data() {
       
    	// Load current dietician test data
        dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_001");
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());
        
        // Get input data
        dieticianInputdata = dieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianInputdata);

        request = request.body(dieticianInputdata);
        request.log().all();
        LoggerLoad.info("Loaded request:" + request);
    }

    @When("Admin send POST http request with endpoint")
    public void admin_send_post_http_request_with_endpoint() {
      
       
    	String endpoint = dieticianTestCase.getEndpoints();
    	 LoggerLoad.info("Endpoint: " + endpoint);
    	response= request.post(endpoint);
    	
        LoggerLoad.info("Status Code: " + response.getStatusCode());
        LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
    }

    @Then("Admin recieves {int} created and with response body")
    public void admin_recieves_created_and_with_response_body(Integer expectedStatusCode) {
        response.prettyPrint();

        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        assertTrue(response.getStatusLine().contains(expectedStatusCode.toString()));
        assertTrue(response.getStatusLine().contains(dieticianTestCase.getExpectedStatusLineMsg()));

    }
}