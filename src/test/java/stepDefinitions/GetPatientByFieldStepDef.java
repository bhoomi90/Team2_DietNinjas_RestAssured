package stepDefinitions;

import io.cucumber.java.en.*;
import org.testng.Assert;
import utilities.CRUDHelper;
import utilities.apiTextContext;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetPatientByFieldStepDef {
	
	RequestSpecification request;
    Response response;
    int patientId;
    
	@Given("API base URI is set")
	public void api_base_uri_is_set() {
		 response = request.get("/morbidity");
	}

	@Given("Request is prepared with no_auth token")
	public void request_is_prepared_with_no_auth_token() {
	   
	   
	}

	@When("User sends GET request to \\/files\\/validFileId")
	public void user_sends_get_request_to_files_valid_file_id() {
	   
	   
	}

	@Then("API should return status code {int}")
	public void api_should_return_status_code(Integer int1) {
	   
	   
	}

	@Then("Response message should indicate unauthorized")
	public void response_message_should_indicate_unauthorized() {
	   
	   
	}

	@Given("Request is prepared with admin_token token")
	public void request_is_prepared_with_admin_token_token() {
	   
	   
	}

	@Then("Response message should indicate forbidden")
	public void response_message_should_indicate_forbidden() {
	   
	   
	}

	@Given("Request is prepared with patient_token token")
	public void request_is_prepared_with_patient_token_token() {
	   
	   
	}

	@Then("Response message should indicate file details")
	public void response_message_should_indicate_file_details() {
	   
	   
	}

	@Given("Request is prepared with dietician_token token")
	public void request_is_prepared_with_dietician_token_token() {
	   
	   
	}

	@Given("Dietician is authenticated with valid token")
	public void dietician_is_authenticated_with_valid_token() {
	   
	   
	}

	@When("Dietician sends POST request to \\/files\\/validFileId")
	public void dietician_sends_post_request_to_files_valid_file_id() {
	   
	   
	}

	@Then("Response message should indicate method not allowed")
	public void response_message_should_indicate_method_not_allowed() {
	   
	   
	}

	@When("Dietician sends GET request to \\/files\\/invalidFileId")
	public void dietician_sends_get_request_to_files_invalid_file_id() {
	   
	   
	}

	@Then("Response message should indicate file not found")
	public void response_message_should_indicate_file_not_found() {
	   
	   
	}

	@When("Dietician sends GET request to \\/files\\/validFileId\\/wrong-path")
	public void dietician_sends_get_request_to_files_valid_file_id_wrong_path() {
	   
	   
	}

	@Then("Response message should indicate endpoint not found")
	public void response_message_should_indicate_endpoint_not_found() {
	   
	   
	}
}