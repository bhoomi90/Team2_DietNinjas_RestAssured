package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import pojo.TestCaseData;
import pojo.loginData;
import utilities.JSONDataReader;
import utilities.LoggerLoad;
import utilities.apiTextContext;
import utilities.configReader;

public class userLoginStepDef {
	
	public String endpoint;
	private TestCaseData loginTestCase;
	private Object currentLoginTest;
	private loginData loginInputData; 
	 private loginData DieticianloginInputdata;
	 private loginData PatientloginInputdata;

	JSONDataReader reader = new JSONDataReader();

	
	

@Given("User creates Post request with request body.Request body : Userlogin and password")
public void user_creates_post_request_with_request_body_request_body_userlogin_and_password() {

        // Fetch login data from loaded JSON test case
    	loginTestCase = Hooks.currentLoginTest;

         loginInputData = loginTestCase.getLoginInputdata();

        // Create the request using RestAssured
         apiTextContext.request = given()
                .baseUri(Hooks.baseUrl)
                .header("Content-Type", "application/json")
                .body(loginInputData);
    }

    @When("User send POST HTTP request with endpoint")
    public void user_send_post_http_request_with_endpoint() {
         endpoint = Hooks.currentLoginTest.getEndpoints();
        apiTextContext.response = apiTextContext.request.post(endpoint);
    }

    @Then("User recieves {int} created with response body")
    public void user_recieves_created_with_response_body(Integer expectedStatusCode) {
    	apiTextContext.response.prettyPrint();  
        // Validate status code
        assertEquals(apiTextContext.response.getStatusCode(), expectedStatusCode.intValue(), "Status code mismatch");

        // Validate status line 
        String expectedStatusLine = "HTTP/1.1 " + expectedStatusCode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg();
        assertEquals(apiTextContext.response.getStatusLine(), expectedStatusLine, "Status line mismatch");
        
        if (apiTextContext.response.getStatusCode() == 200) {
            
            String role = apiTextContext.response.jsonPath().getString("roles[0]");
            String token = apiTextContext.response.jsonPath().getString("token");

            switch (role) {
                case "ROLE_DIETICIAN":
                    apiTextContext.dieticianToken = token;
                    LoggerLoad.info("Token saved for ROLE_DIETICIAN: " + apiTextContext.dieticianToken);
                    break;

                case "ROLE_ADMIN":
                   apiTextContext.authToken = token;
                    LoggerLoad.info("Token saved for ROLE_ADMIN: " + apiTextContext.authToken);
                    break;

                case "ROLE_PATIENT":
                    apiTextContext.patientToken = token;
                    LoggerLoad.info("Token saved for ROLE_PATIENT: " + apiTextContext.patientToken);
                    break;

                default:
                    LoggerLoad.warn("Unrecognized role: " + role);
                    break;
            }
        }
    }
	

	@Given("User creates Post request with invalid credential")
	public void user_creates_post_request_with_invalid_credential() {
		
		LoggerLoad.info("Sent a post request with invalid inputs");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_002");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());

		 loginInputData = Hooks.currentLoginTest.getLoginInputdata();

		 apiTextContext.request = given()
		            .baseUri(Hooks.baseUrl)
		            .contentType(ContentType.JSON)
		            .body(loginInputData);
	}

	@Then("User recieves {int} unauthorized")
	public void user_recieves_unauthorized(Integer expectedsatatuscode) {
		LoggerLoad.info("validate response body");
		apiTextContext.response.then()
        .statusCode(expectedsatatuscode)
        .statusLine("HTTP/1.1 " + expectedsatatuscode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
        .log().all();
		
	    
	}

	@Given("User creates GET request with request body.Request body : Userlogin and password")
	public void user_creates_get_request_with_request_body_request_body_userlogin_and_password()  {
		LoggerLoad.info("Sent a post request with invalid inputs");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_003");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());

		 loginInputData = Hooks.currentLoginTest.getLoginInputdata();
		 apiTextContext. request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.JSON)
		            .body(loginInputData);
	}

	@When("User send GET HTTP request with endpoint")
	public void user_send_get_http_request_with_endpoint() {
	   
		apiTextContext.response = apiTextContext.request.when().get(Hooks.currentLoginTest.getEndpoints());
	}

	@Then("User recieves {int} method not allowed")
	public void user_recieves_method_not_allowed(Integer expectedstatuscode) {
		apiTextContext.response.then().statusCode(expectedstatuscode)
		.statusLine("HTTP/1.1 " + expectedstatuscode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
		.body("error", equalTo("Method Not Allowed"))
		.log().all();
	    
	}

	@When("User send POST HTTP request with invalid endpoint")
	public void user_send_post_http_request_with_invalid_endpoint() throws IOException, ParseException {
		LoggerLoad.info("Sent a post request with invalid endpoint");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_004");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());

		 loginInputData = Hooks.currentLoginTest.getLoginInputdata();

		 apiTextContext. response = apiTextContext.request.when().post(Hooks.currentLoginTest.getEndpoints());
	}
  
	@Given("User creates Post request with request body and invalid content type.Request body : Userlogin and password")
	public void user_creates_post_request_with_request_body_and_invalid_content_type_request_body_userlogin_and_password() throws StreamReadException, DatabindException, IOException, ParseException {
	    		
		LoggerLoad.info("Sent a post request with invalid content type");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_005");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());

		 loginInputData = Hooks.currentLoginTest.getLoginInputdata();
		 
		 ObjectMapper mapper = new ObjectMapper();
		 String jsonBody = mapper.writeValueAsString(loginInputData);
		
		 apiTextContext.request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.TEXT)
		            .body(jsonBody);
	}

	@Then("User recieves {int} unsupported media type")
	public void user_recieves_unsupported_media_type(Integer expectedstatuscode) {
	    
		apiTextContext.response.then().statusCode(expectedstatuscode)
		.statusLine("HTTP/1.1 " + expectedstatuscode + " " + Hooks.currentLoginTest.getExpectedStatusLineMsg())
		.log().all();
	}
	
	@Given("User creates Post request with dietician input request body.Request body : Userlogin and password")
	public void user_creates_post_request_with_dietician_input_request_body_request_body_userlogin_and_password() {
	   
		LoggerLoad.info("Sent a post request with valid dietician credentials");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_006");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());

		DieticianloginInputdata = Hooks.currentLoginTest.getDieticianloginInputdata();
		// Override the fields with values from response (captured earlier)
		DieticianloginInputdata.setUserLoginEmail(DieticianStepDef.dieticianEmail);  // captured from response
		DieticianloginInputdata.setPassword(DieticianStepDef.dieticianLoginPwd);     // captured from response

		LoggerLoad.info("Overriding email and password with captured values:");
		LoggerLoad.info("Email: " + DieticianStepDef.dieticianEmail);
		LoggerLoad.info("Password: " + DieticianStepDef.dieticianLoginPwd);
		 apiTextContext.request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.JSON)
		            .body(DieticianloginInputdata);
		
	}
	
	@Given("User creates Post request with invalid dietician credential")
	public void user_creates_post_request_with_invalid_dietician_credential() {
		
		LoggerLoad.info("Sent a post request with valid patient credentials");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_007");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());

		DieticianloginInputdata = Hooks.currentLoginTest.getDieticianloginInputdata();
		 apiTextContext.request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.JSON)
		            .body(DieticianloginInputdata);
	    
	}
	
	@Given("User creates Post request with patient input request body.Request body : Userlogin and password")
	public void user_creates_post_request_with_patient_input_request_body_request_body_userlogin_and_password() {
		LoggerLoad.info("Sent a post request with invalid dietician credentials");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_008");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());

		PatientloginInputdata = Hooks.currentLoginTest.getPatientloginInputdata();
		 apiTextContext.request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.JSON)
		            .body(PatientloginInputdata);
		
	}
	
	
	
	@Given("User creates Post request with invalid patient credential")
	public void user_creates_post_request_with_invalid_patient_credential() {
	   
		LoggerLoad.info("Sent a post request with invalid patient credentials");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLoginTests(), "LT_009");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());

		PatientloginInputdata = Hooks.currentLoginTest.getPatientloginInputdata();
		 apiTextContext.request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.JSON)
		            .body(PatientloginInputdata);
	}
	
	@Given("User creates GET request")
	public void user_creates_get_request() {
	  
		LoggerLoad.info("Sent a post request with No authorization");
		Hooks.currentLoginTest = JSONDataReader.getTestCaseById(Hooks.allTestData.getLogoutTests(), "LOT_001");
		LoggerLoad.info("Loaded Login Test Case: " + Hooks.currentLoginTest.getScenario());
		
		apiTextContext.request = given().baseUri(Hooks.baseUrl);
				

		
	}
	

}
