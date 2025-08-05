package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.cucumber.java.en.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.PatientPojo;
import pojo.PatientTestData;
import pojo.VitalsPojo;
import utilities.LoggerLoad;

public class ExistingPatientStepDef {
	List<PatientTestData> existingPatientTests;
	PatientTestData patientData;
	RequestSpecification request;
	Response response;
	String dieticianToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MzI3Njc5LCJleHAiOjE3NTQzNTY0Nzl9.EUtT9arbxSgx28jPbWePHPpiF5V_kyUQzQvzlEFqY6CITpCp77G2lRv2kCO_LR9N-rekDNZOmGo5KBIxwzMG4w";

//-----------No Auth--------------

@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields and valid patient ID for existing patient with {string}")
public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id_for_existing_patient_with(String scenarioName) throws IOException {
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
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
	    	//.multiPart("file", file, "application/pdf")
	    	.multiPart("vitals", vitalsInfoJson);
}

@When("Dietician send PUT http request with endpoint for existing patient with {string}")
public void dietician_send_put_http_request_with_endpoint_for_existing_patient_with() {
	   String endpoint = patientData.getEndpoint();
	    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
	    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
	    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
	    		
	   	response = request.when().put(addedEndpoint);
	    response.then().log().all();
}

@Then("Dietician recieves {int} unauthorized for existing patient")
public void dietician_recieves_unauthorized_for_existing_patient(Integer expectedStatusCode) {
	response.then().log().all().statusCode(expectedStatusCode);
	assertEquals(response.jsonPath().getString("FirstName"), patientData.getPatientDataInput().getFirstname());
	assertEquals(response.jsonPath().getString("LastName"), patientData.getPatientDataInput().getLastname());
	assertEquals(response.jsonPath().getString("ContactNumber"), patientData.getPatientDataInput().getContactNumber());
	assertEquals(response.jsonPath().getString("DateOfBirth"), patientData.getPatientDataInput().getDateOfBirth());
}


//----------------Admin bearer token --------------
	
@Given("Admin creates PUT request by entering valid data into the form-data key and value fields and valid patient ID for existing patient {string}")
public void admin_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id_for_existing_patient(String scenarioName) throws IOException{
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
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


//---------------Patient Bearer token---------------

@Given("Patient creates PUT request by entering valid data into the form-data key and value fields and valid patient ID with {string}")
public void patient_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id_with(String scenarioName)throws IOException {
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
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


@When("Patient send PUT http request with endpoint for existing patient with {string}")
public void patient_send_put_http_request_with_endpoint_for_existing_patient_with(String string) {
    // Write code here that turns the phrase above into concrete actions
    throw new io.cucumber.java.PendingException();
}

@Then("Patient recieves {int} forbidden for existing patient")
public void patient_recieves_forbidden_for_existing_patient(Integer expectedStatusCode) {
	response.then().log().all().statusCode(expectedStatusCode);
}
//----------------Dietician Bearer token------------	
	
@Given("Dietician creates PUT request by entering valid data. \\( Mandatory and additional details) into the form-data key and value fields valid patient ID {string}")
public void dietician_creates_put_request_by_entering_valid_data_mandatory_and_additional_details_into_the_form_data_key_and_value_fields_valid_patient_id(String scenarioName) throws IOException{
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
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
	    	//.multiPart("file", file, "application/pdf")
	    	.multiPart("vitals", vitalsInfoJson);
}

@When("Dietician send PUT http request with endpoint for Existing patient")
public void dietician_send_put_http_request_with_endpoint_for_existing_patient() {
	  String endpoint = patientData.getEndpoint();
	    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
	    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
	    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
	    		
	   	response = request.when().put(addedEndpoint);
	    response.then().log().all();
}

@Then("Dietician recieves {int} ok and with Existingd response body.")
public void dietician_recieves_ok_and_with_existingd_response_body(Integer expectedStatusCode) {
	response.then().log().all().statusCode(expectedStatusCode);
	assertEquals(response.jsonPath().getString("FirstName"), patientData.getPatientDataInput().getFirstname());
	assertEquals(response.jsonPath().getString("LastName"), patientData.getPatientDataInput().getLastname());
	assertEquals(response.jsonPath().getString("ContactNumber"), patientData.getPatientDataInput().getContactNumber());
	assertEquals(response.jsonPath().getString("DateOfBirth"), patientData.getPatientDataInput().getDateOfBirth());
	}

@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields except valid vitals data and valid patient ID {string}")
public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_except_valid_vitals_data_and_valid_patient_id(String scenarioName) throws IOException {
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
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

@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields except file and valid patient ID {string}")
public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_except_file_and_valid_patient_id(String scenarioName) throws IOException {
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
		if(patientTest.getScenarioName().equals(scenarioName)) {
			patientData = patientTest;
			LoggerLoad.info("PUT request sent: Breaking loop");
			break;
		}
	}
    PatientPojo patientPojo = patientData.getPatientDataInput();
    String patientInfoJson = mapper.writeValueAsString(patientPojo);
    LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
    request = given()
	    	.baseUri(Hooks.baseUrl)
	    	.header("Authorization", "Bearer " +dieticianToken)
	    	.contentType(ContentType.MULTIPART)
	    	.multiPart("patientInfo", patientInfoJson);
}

@Given("Dietician creates PUT request by entering valid data \\( Mandatory only) into the form-data key and value fields and valid patient ID {string}")
public void dietician_creates_put_request_by_entering_valid_data_mandatory_only_into_the_form_data_key_and_value_fields_and_valid_patient_id(String scenarioName) throws IOException {
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
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
	    	//.multiPart("file", file, "application/pdf")
	    	.multiPart("vitals", vitalsInfoJson);
	  	    
}

@Given("Dietician creates PUT request by entering invalid data \\( Additional details only) into the form-data key and value fields and valid patient ID {string}")
public void dietician_creates_put_request_by_entering_invalid_data_additional_details_only_into_the_form_data_key_and_value_fields_and_valid_patient_id(String scenarioName) throws IOException {
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
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

@Then("Dietician recieves {int} Bad request")
public void dietician_recieves_bad_request_for_Existing_patient(Integer  expectedStatusCode, String errorMessage) {
	response.then().log().all().statusCode(expectedStatusCode);
	assertTrue(response.jsonPath().getString("errorCode").contains("INVALID_REQ_DATA"));
	assertTrue(response.jsonPath().getString("errorMessage").contains(errorMessage));
}

@Then("Dietician recieves {int} not found for Existing patient")
public void dietician_recieves_not_found_for_Existing_patient(Integer expectedStatusCode, String errorMessage) {
	response.then().log().all().statusCode(expectedStatusCode);
	//assertTrue(response.jsonPath().getString("errorCode").contains("NOT_FOUND"));
	assertTrue(response.jsonPath().getString("errorMessage").contains(errorMessage));
}

@Given("Dietician creates POST request by entering valid data into the form-data key and value fields and valid patient ID {string}")
public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_valid_patient_id(String scenarioName) throws IOException  {
	 ObjectMapper mapper = new ObjectMapper();
	 existingPatientTests = Hooks.allTestData.getPatientTests();
	
	    for(PatientTestData patientData : existingPatientTests) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
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
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    	   		.multiPart("file", file, "application/pdf")
	    		    .multiPart("vitals", vitalsInfoJson);
	    	    
	    	    LoggerLoad.info("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }
	 }
	    
@When("Dietician send POST http request with endpoint for Existing patient")
public void dietician_send_post_http_request_with_endpoint_for_existing_patient() {
	String endpoint = patientData.getEndpoint();
    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
    		
   	response = request.when().post(addedEndpoint);
    response.then().log().all();
}

@Then("Dietician recieves {int} method not allowed for existing patient")
public void dietician_recieves_method_not_allowed(Integer expectedStatusCode, String errorMessage) {
	response.then().log().all().statusCode(expectedStatusCode);
	assertTrue(response.jsonPath().getString("error").contains("Method Not Allowed"));
	assertTrue(response.jsonPath().getString("message").contains(errorMessage));
}

@When("Dietician sent PUT http request with invalid endpoint for Existing patient")
public void dietician_sent_put_http_request_with_invalid_endpoint_for_existing_patient() {
    String endpoint = patientData.getEndpoint();
    LoggerLoad.info("Endpoint from JSON file: " + endpoint);
    String addedEndpoint = endpoint.replace("{paientId}", CreatePatientStepDef.patientId);
    LoggerLoad.info("Resolved Endpoint: " + addedEndpoint);
    		
   	response = request.when().put(addedEndpoint);
    response.then().log().all();
}

@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields and invalid content type {string}")
public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_invalid_content_type(String scenarioName) throws IOException {
	ObjectMapper mapper = new ObjectMapper();
	existingPatientTests = Hooks.allTestData.getExistingPatientTests();
	LoggerLoad.info("Going to " +scenarioName);
	
	for(PatientTestData patientTest : existingPatientTests) {
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

@Then("Dietician recieves {int} unsupported media type")
public void dietician_recieves_unsupported_media_type(Integer expectedStatusCode, String errorMessage) {
	response.then().log().all().statusCode(expectedStatusCode);
	assertTrue(response.jsonPath().getString("error").contains("Unsupported Media Type"));
	assertTrue(response.jsonPath().getString("message").contains(errorMessage));
}
}