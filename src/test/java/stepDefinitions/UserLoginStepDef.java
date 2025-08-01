package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.TestCaseData;
import pojo.loginData;

public class UserLoginStepDef {

    private RequestSpecification request;
    private Response response; 
    public static String authToken; // <-- save token globally
    TestCaseData loginTestCase;
    
    @Given("User creates Post request with request body. Request body : Userlogin and password")
    public void user_creates_post_request_with_request_body_request_body_userlogin_and_password() {

        // Fetch login data from loaded JSON test case
    	loginTestCase = Hooks.currentLoginTest;

        loginData loginInputData = loginTestCase.getLoginInputdata();

        // Create the request using RestAssured
        request = given()
                .baseUri(Hooks.baseUrl)
                .header("Content-Type", "application/json")
                .body(loginInputData);
    }

    @When("User send POST HTTP request with endpoint")
    public void user_send_post_http_request_with_endpoint() {
        String endpoint = Hooks.currentLoginTest.getEndpoints();
        response = request.post(endpoint);
    }

    @Then("User recieves {int} created with response body")
    public void user_recieves_created_with_response_body(Integer expectedStatusCode) {
        response.prettyPrint();  
        // Validate status code
        assertEquals(response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        // Validate status line 
        String expectedStatusLine = "HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg();
        assertEquals(response.getStatusLine(), expectedStatusLine, "Status line mismatch");
        
     //  Extract and save the token from response
        authToken = response.jsonPath().getString("token");
        System.out.println("Token saved for future requests: " + authToken);
    }
}
