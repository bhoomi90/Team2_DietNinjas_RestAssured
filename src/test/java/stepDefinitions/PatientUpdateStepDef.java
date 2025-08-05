package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.PatientPojo;
import pojo.PatientTestData;
import pojo.VitalsPojo;
import utilities.LoggerLoad;

public class PatientUpdateStepDef {

	List<PatientTestData> updatePatientTests;
	PatientTestData patientData;
	RequestSpecification request;
	Response response;
	String dieticianToken = CreatePatientStepDef.dieticianToken;
	String adminToken = CreatePatientStepDef.adminToken;
	String patientToken = CreatePatientStepDef.patientToken;
	
	@Given("Dietician creates PUT request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields with {string}.")
	public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException{ 
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    //File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +dieticianToken)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson)
		    	//.multiPart("file", file, "application/pdf")
		    	.multiPart("vitals", vitalsInfoJson);
		    	    
	}

	@When("Dietician send PUT http request with endpoint to update patient")
	public void dietician_send_put_http_request_with_endpoint_to_update_patient() {

	    String endpoint = patientData.getEndpoint();
	    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
	    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
	    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
	    		
	   	response = request.when().put(addedEndpoint);
	    response.then().log().all();

	}

	@Then("Dietician recieves {int} ok and with updated response body after update patient request.")
	public void dietician_recieves_ok_and_with_updated_response_body_after_update_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertEquals(response.jsonPath().getString("FirstName"), patientData.getPatientDataInput().getFirstname());
		assertEquals(response.jsonPath().getString("LastName"), patientData.getPatientDataInput().getLastname());
		assertEquals(response.jsonPath().getString("ContactNumber"), patientData.getPatientDataInput().getContactNumber());
		assertEquals(response.jsonPath().getString("DateOfBirth"), patientData.getPatientDataInput().getDateOfBirth());
		//assertEquals(response.jsonPath().getString("Weight"), patientData.getVitalsDataInput().getWeight());
	}
	
	@Given("Dietician creates PUT request by entering only valid mandatory details into the form-data key and value fields with {string}.")
	public void dietician_creates_put_request_by_entering_only_valid_mandatory_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
//	    String filePath = patientData.getFile();
//	    LoggerLoad.info("Sending file as: " +filePath);
//	    File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +dieticianToken)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson);
		    	//.multiPart("file", file, "application/pdf")
		    	//.multiPart("vitals", vitalsInfoJson);
	}
	
	@Given("Dietician creates PUT request by entering only valid additional details into the form-data key and value fields with {string}.")
	public void dietician_creates_put_request_by_entering_only_valid_additional_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +dieticianToken)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson)
		    	.multiPart("file", file, "application/pdf")
		    	.multiPart("vitals", vitalsInfoJson);
	}

	@Then("Dietician recieves {int} Bad request after update patient request")
	public void dietician_recieves_bad_request_after_update_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
	}
	
	@Given("Dietician creates PUT request by entering only invalid additional details into the form-data key and value fields with {string}.")
	public void dietician_creates_put_request_by_entering_only_invalid_additional_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +dieticianToken)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson)
		    	.multiPart("file", file, "application/pdf")
		    	.multiPart("vitals", vitalsInfoJson);
	}
	
	@Then("Dietician recieves {int} Bad request after update patient request with {string}")
	public void dietician_recieves_bad_request_after_update_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("errorCode").contains("INVALID_REQ_DATA"));
		assertTrue(response.jsonPath().getString("errorMessage").contains(errorMessage));
	}
	
	@When("Dietician send PUT http request with endpoint to update patient with invalid patientID")
	public void dietician_send_put_http_request_with_endpoint_to_update_patient_with_invalid_patient_id() {
	    String endpoint = patientData.getEndpoint();
	    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
	    String addedEndpoint = endpoint.replace("{paientId}", "1000100");
	    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
	    		
	   	response = request.when().put(addedEndpoint);
	    response.then().log().all();
	}

	@Then("Dietician recieves {int} not found after update patient request with {string}")
	public void dietician_recieves_not_found_after_update_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		//assertTrue(response.jsonPath().getString("errorCode").contains("NOT_FOUND"));
		assertTrue(response.jsonPath().getString("errorMessage").contains(errorMessage));
	}
	
	@Given("Dietician creates PUT request by not attaching any file into the form-data key and value fields with {string}.")
	public void dietician_creates_put_request_by_not_attaching_any_file_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    //File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +dieticianToken)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson)
		    	//.multiPart("file", file, "application/pdf")
		    	.multiPart("vitals", vitalsInfoJson);
	}
	
	@Given("Dietician create PUT request by entering valid data into the form-data key and value fields with {string}.")
	public void dietician_create_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +dieticianToken)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson)
		    	.multiPart("file", file, "application/pdf")
		    	.multiPart("vitals", vitalsInfoJson);
	}
	
	@When("Dietician send POST http request with endpoint to update patient")
	public void dietician_send_post_http_request_with_endpoint_to_update_patient() {
	    String endpoint = patientData.getEndpoint();
	    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
	    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
	    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
	    		
	   	response = request.when().post(addedEndpoint);
	    response.then().log().all();
	}

	@Then("Dietician recieves {int} method not allowed after update patient request with {string}")
	public void dietician_recieves_method_not_allowed_after_update_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("error").contains("Method Not Allowed"));
		assertTrue(response.jsonPath().getString("message").contains(errorMessage));
	}

	@When("Dietician sent PUT http request with invalid endpoint to update patient")
	public void dietician_sent_put_http_request_with_invalid_endpoint_to_update_patient() {
	    String endpoint = patientData.getEndpoint();
	    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
	    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
	    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
	    		
	   	response = request.when().put(addedEndpoint);
	    response.then().log().all();
	}

	@Then("Dietician recieve {int} not found after update patient request with {string}")
	public void dietician_recieve_not_found_after_update_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("error").contains("Not Found"));
		assertTrue(response.jsonPath().getString("message").contains(errorMessage));
	}
	
	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type with {string}")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_invalid_content_type_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    //File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +dieticianToken)
		    	.contentType(ContentType.URLENC)
		    	.formParam("patientInfo", patientInfoJson)
		    	//.formParam("file", file, "application/pdf")
		    	.formParam("vitals", vitalsInfoJson);
	}

	@Then("Dietician recieves {int} unsupported media type after update patient request with {string}")
	public void dietician_recieves_unsupported_media_type_after_update_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("error").contains("Unsupported Media Type"));
		assertTrue(response.jsonPath().getString("message").contains(errorMessage));
	}
	
	// Update patient with admin token
	@Given("Admin creates PUT request by entering valid data into the form-data key and value fields with {string}.")
	public void admin_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    //File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +adminToken)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson)
		    	//.multiPart("file", file, "application/pdf")
		    	.multiPart("vitals", vitalsInfoJson);
	}

	@When("Admin send PUT http request with endpoint to update patient")
	public void admin_send_put_http_request_with_endpoint_to_update_patient() {
	    String endpoint = patientData.getEndpoint();
	    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
	    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
	    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
	    		
	   	response = request.when().put(addedEndpoint);
	    response.then().log().all();
	}

	@Then("Admin recieves {int} forbidden after update patient request")
	public void admin_recieves_forbidden_after_update_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
	}
	
	@Given("Dietician generate PUT request by entering valid data into the form-data key and value fields with {string}.")
	public void dietician_generate_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    //File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson)
		    	//.multiPart("file", file, "application/pdf")
		    	.multiPart("vitals", vitalsInfoJson);
	}

	@Then("Dietician recieves {int} unauthorized after update patient request")
	public void dietician_recieves_unauthorized_after_update_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
	}
	
	@Given("Patient creates PUT request by entering valid data into the form-data key and value fields with {string}.")
	public void patient_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		updatePatientTests = Hooks.allTestData.getUpdatePatientTests();
		LoggerLoad.info("Going to " +scenarioName);
		
		for(PatientTestData patientTest : updatePatientTests) {
			if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("PUT request sent: Breaking loop");
				break;
			}
		}
	    PatientPojo patientPojo = patientData.getPatientDataInput();
	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    String filePath = patientData.getFile();
	    LoggerLoad.info("Sending file as: " +filePath);
	    File file = new File(filePath);
	    		
	    VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    request = given()
		    	.baseUri(Hooks.baseUrl)
		    	.header("Authorization", "Bearer " +patientToken)
		    	.contentType(ContentType.MULTIPART)
		    	.multiPart("patientInfo", patientInfoJson)
		    	.multiPart("file", file, "application/pdf")
		    	.multiPart("vitals", vitalsInfoJson);
	}

	@When("Patient send PUT http request with endpoint to update patient")
	public void patient_send_put_http_request_with_endpoint_to_update_patient() {
	    String endpoint = patientData.getEndpoint();
	    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
	    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
	    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
	    		
	   	response = request.when().put(addedEndpoint);
	    response.then().log().all();
	}

	@Then("Patient recieves {int} forbidden after update patient request")
	public void patient_recieves_forbidden_after_update_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
	}
}