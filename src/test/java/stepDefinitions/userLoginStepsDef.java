package stepDefinitions;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.userLoginPojo;
import utilities.configReader;

public class userLoginStepsDef {
	
	List<userLoginPojo> loginDataList;
	userLoginPojo userpojo;
	RequestSpecification request;
	Response response;
	String endpoint;

	@Given("User creates Post request with request body.Request body : Userlogin and password")
	public void user_creates_post_request_with_request_body_request_body_userlogin_and_password() throws IOException {
	    ObjectMapper mapper = new ObjectMapper();

	    // Read and convert all JSON entries to List of POJOs
	    loginDataList = mapper.readValue(
	        new File("src/test/resources/testData/jsonData.json"),
	        new TypeReference<List<userLoginPojo>>() {}
	    );

	    userpojo = loginDataList.get(0); 
	    endpoint = userpojo.getEndpoint(); 

	    String jsonBody = mapper.writeValueAsString(userpojo);

	    request = given()
	            .baseUri(configReader.getProperty("baseURL"))
	            .contentType(ContentType.JSON)
	            .body(jsonBody);
	}

    @When("User send POST HTTP request with endpoint")
    public void user_send_post_http_request_with_endpoint() {
    	response = request.when().post(endpoint);

    	if (response.getStatusCode() == 200 && response.jsonPath().get("token") != null) {
            String token = response.jsonPath().getString("token");

            userpojo.setAdminToken(token);

             System.out.println("Token set in userLoginPojo: " + userpojo.getAdminToken());
    	}		
       
    }

    @Then("User recieves {int} created with response body")
    public void user_recieves_created_with_response_body(Integer expectedStatusCode) {
        response.then().log().all();
        response.then().statusCode(expectedStatusCode);
    }
	@Given("User creates Post request with invalid credential")
	public void user_creates_post_request_with_invalid_credential() throws IOException {
	    
		    ObjectMapper mapper = new ObjectMapper();

		    // Read and convert all JSON entries to List of POJOs
		    loginDataList = mapper.readValue(new File("src/test/resources/testData/jsonData.json"),new TypeReference<List<userLoginPojo>>() {});
		    
		    userpojo = loginDataList.get(1); 
		    endpoint = userpojo.getEndpoint(); 

		    String jsonBody = mapper.writeValueAsString(userpojo);

		    request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.JSON)
		            .body(jsonBody);
	}

	@Then("User recieves {int} unauthorized")
	public void user_recieves_unauthorized(Integer expectedsatatuscode) {
		response.then()
        .statusCode(expectedsatatuscode)
        .log().all();
		
	    
	}

	@Given("User creates GET request with request body.Request body : Userlogin and password")
	public void user_creates_get_request_with_request_body_request_body_userlogin_and_password() throws StreamReadException, DatabindException, IOException {
	    
		 ObjectMapper mapper = new ObjectMapper();

		    // Read and convert all JSON entries to List of POJOs
		    loginDataList = mapper.readValue(new File("src/test/resources/testData/jsonData.json"),new TypeReference<List<userLoginPojo>>() {});
		    
		    userpojo = loginDataList.get(2); 
		    endpoint = userpojo.getEndpoint(); 

		    String jsonBody = mapper.writeValueAsString(userpojo);

		    request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.JSON)
		            .body(jsonBody);
	}

	@When("User send GET HTTP request with endpoint")
	public void user_send_get_http_request_with_endpoint() {
	   
		response = request.when().get(endpoint);
	}

	@Then("User recieves {int} method not allowed")
	public void user_recieves_method_not_allowed(Integer expectedstatuscode) {
		response.then().statusCode(expectedstatuscode)
		.log().all();
	    
	}

	@When("User send POST HTTP request with invalid endpoint")
	public void user_send_post_http_request_with_invalid_endpoint() {
		
		 
	    userpojo = loginDataList.get(3); 
	    endpoint = userpojo.getEndpoint(); 
	    response = request.when().get(endpoint);
	}

	@Given("User creates Post request with request body and invalid content type.Request body : Userlogin and password")
	public void user_creates_post_request_with_request_body_and_invalid_content_type_request_body_userlogin_and_password() throws StreamReadException, DatabindException, IOException {
	    
		 ObjectMapper mapper = new ObjectMapper();

		    // Read and convert all JSON entries to List of POJOs
		    loginDataList = mapper.readValue(new File("src/test/resources/testData/jsonData.json"),new TypeReference<List<userLoginPojo>>() {});
		    
		    userpojo = loginDataList.get(2); 
		    endpoint = userpojo.getEndpoint(); 

		    String jsonBody = mapper.writeValueAsString(userpojo);

		    request = given()
		            .baseUri(configReader.getProperty("baseURL"))
		            .contentType(ContentType.TEXT)
		            .body(jsonBody);
	}

	@Then("User recieves {int} unsupported media type")
	public void user_recieves_unsupported_media_type(Integer expectedstatuscode) {
	    
		response.then().statusCode(expectedstatuscode)
		.log().all();
	}
	
	@Given("User creates Post request with dietician input request body.Request body : Userlogin and password")
	public void user_creates_post_request_with_dietician_input_request_body_request_body_userlogin_and_password() {
	   
		
	}
	
	@Given("User creates Post request with patient input request body.Request body : Userlogin and password")
	public void user_creates_post_request_with_patient_input_request_body_request_body_userlogin_and_password() {
	    
		
	}
	
	@Given("User creates Post request with invalid dietician credential")
	public void user_creates_post_request_with_invalid_dietician_credential() {
	    
	}
	
	@Given("User creates Post request with invalid patient credential")
	public void user_creates_post_request_with_invalid_patient_credential() {
	   
		
	}

}
