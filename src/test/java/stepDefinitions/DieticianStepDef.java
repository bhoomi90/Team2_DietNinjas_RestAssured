package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
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
    private TestCaseData dieticianTestCase;
    dieticianData DieticianInputdata;

    @Given("Set admin bearer token")
    public void set_admin_bearer_token() {
        // Use bearer token from login
        String token = UserLoginStepDef.authToken;
        if (token == null || token.isEmpty()) {
            throw new RuntimeException("Token not set from login. Make sure login scenario runs before this.");
        }

        // Load current dietician test data
        dieticianTestCase = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_001");
        LoggerLoad.info("Loaded Dietician Test Case: " + dieticianTestCase.getScenario());
        // Prepare request with token
        request = given()
                .baseUri(Hooks.baseUrl)
                .header("Authorization", "Bearer " + token)
                .header("Content-Type", "application/json");
        		
    }

    @Given("Admin creates POST request with valid data")
    public void admin_creates_post_request_with_valid_data() {
       

        // Get input data
       DieticianInputdata = dieticianTestCase.getDieticianInputdata();
        LoggerLoad.info("Loaded Dietician Test Case: " + DieticianInputdata);

        // Correct way to set the body and then log the request
        request = request.body(DieticianInputdata);
        request.log().all();
    }

    @When("Admin send POST http request with endpoint")
    public void admin_send_post_http_request_with_endpoint() {
       // response = request.post(dieticianTestCase.getEndpoints());
    	String endpoint = JSONDataReader.getTestCaseById(Hooks.allTestData.getDieticianTests(), "DT_001").getEndpoints();
    	 LoggerLoad.info("Status Code: " + endpoint);
    	response= request.post(endpoint);
    	
        LoggerLoad.info("Status Code: " + response.getStatusCode());
        LoggerLoad.info("Response Body: " + response.getBody().asPrettyString());
    }

    @Then("Admin recieves {int} created and with response body")
    public void admin_recieves_created_and_with_response_body(Integer expectedStatusCode) {
        response.prettyPrint();

        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        String expectedStatusLine = "HTTP/1.1 " + expectedStatusCode + " " + dieticianTestCase.getExpectedStatusLineMsg();
        assertEquals(response.getStatusLine(), expectedStatusLine, "Status line mismatch");
    }
}