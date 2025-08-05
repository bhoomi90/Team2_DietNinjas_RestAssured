package stepDefinitions;
import io.cucumber.java.en.*;

public class GetPatientsMorbidityDetStepDef {

	@Given("Login as a user with dietician login information as in {string} for creating patient")
	public void login_as_a_user_with_dietician_login_information_as_in_for_creating_patient(String string) {
	   
	}

	@Given("Create a new patient using {string}")
	public void create_a_new_patient_using(String string) {
	   
	}

	@Given("Dietician create GET request  with no auth")
	public void dietician_create_get_request_with_no_auth() {
	   
	}

	@When("Dietician send GET http request with endpoint")
	public void dietician_send_get_http_request_with_endpoint() {
	   
	   
	}

	@Then("Dietician recieves {int} unauthorized status code {int}")
	public void dietician_recieves_unauthorized_status_code(Integer int1, Integer int2) {
	   
	   
	}

	@Given("Admin create GET request  with admin auth")
	public void admin_create_get_request_with_admin_auth() {
	   
	   
	}

	@When("Admin  send GET http request with endpoint")
	public void admin_send_get_http_request_with_endpoint() {
	   
	   
	}

	@Then("Admin recieves {int} Forbidden  status code {int}")
	public void admin_recieves_forbidden_status_code(Integer int1, Integer int2) {
	   
	   
	}

	@Given("Patient create GET request  with patient token")
	public void patient_create_get_request_with_patient_token() {
	   
	   
	}

	@When("Patient send GET http request with endpoint")
	public void patient_send_get_http_request_with_endpoint() {
	   
	   
	}

	@Then("Patient recieves {int} ok with details of the patient id {int}")
	public void patient_recieves_ok_with_details_of_the_patient_id(Integer int1, Integer int2) {
	   
	   
	}

	@Given("Dietician create GET request with dietician token")
	public void dietician_create_get_request_with_dietician_token() {
	   
	   
	}

	@Given("Dietician create POST request with dietician token")
	public void dietician_create_post_request_with_dietician_token() {
	   
	   
	}

	@When("Dietician send POST http request with endpoint")
	public void dietician_send_post_http_request_with_endpoint() {
	   
	   
	}

	@Then("Dietician recieves {int} method not allowed statusCode {int}")
	public void dietician_recieves_method_not_allowed_status_code(Integer int1, Integer int2) {
	   
	   
	}

	@Then("Dietician recieves {int} not found statusCode {int}")
	public void dietician_recieves_not_found_status_code(Integer int1, Integer int2) {
	   
	   
	}

	@When("Dietician send GET http request with invalid endpoint")
	public void dietician_send_get_http_request_with_invalid_endpoint() {
	   
	   
	}

	
}