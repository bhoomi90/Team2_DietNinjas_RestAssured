package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.TestCaseData;
import pojo.loginData;
import utilities.JSONDataReader;
import utilities.LoggerLoad;

public class AdminLoginStepDef {

    private RequestSpecification request;
    private Response response; 
    TestCaseData currentLoginTest;
    private loginData loginInputData;
    public static String authToken; // <-- save token globally
    TestCaseData loginTestCase;
    
    @Given("User creates Post request with request body. Request body : Userlogin and password for admin login feature")
    public void user_creates_post_request_with_request_body_request_body_userlogin_and_password_for_admin_login_feature() {
    	
    	
    	Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_001");
    	LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());
    	
    	// Fetch login data from loaded JSON test case
    	 loginInputData = Hooks.currentLoginTest.getLoginInputdata();
    	
        // Create the request using RestAssured
        request = given()
                .baseUri(Hooks.baseUrl)
                .header("Content-Type", "application/json")
                .body(loginInputData);
    }

    @When("User send POST HTTP request with endpoint for admin login feature")
    public void user_send_post_http_request_with_endpoint_for_admin_login_feature() {
       
    	 String endpoint = Hooks.currentLoginTest.getEndpoints();
         response = request.post(endpoint);
    }

    @Then("User recieves {int} created with response body for admin login feature")
    public void user_recieves_created_with_response_body_for_admin_login_feature(Integer expectedStatusCode) {
        
    	response.prettyPrint();  
        // Validate status code
        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        // Validate status line 
        String expectedStatusLine = "HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg();
        assertEquals(response.getStatusLine(), expectedStatusLine, "Status line mismatch");
        
     //  Extract and save the token from response
        if(expectedStatusCode ==200) {
        	
        authToken = response.jsonPath().getString("token");
        System.out.println("Token saved for future requests: " + authToken);
        LoggerLoad.info("Token saved for future requests: "+authToken);
        
          }
    } 
}
