package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
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
import utilities.configReader;

public class createPatientStepsDef {

	List<PatientTestData> patientDataList;
	//PatientPojo patientPojo;
	RequestSpecification request;
	Response response;
	
	// Create Patient without Bearer token
	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields with {string}.")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))	    	    	
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }	    
	}

	@Then("Dietician recieves {int} unauthorized after create patient request")
	public void dietician_recieves_unauthorized_after_create_patient_request(Integer expectedStatusCode) {
	    response.then().log().all().statusCode(expectedStatusCode);
	    assertEquals(response.jsonPath().getString("error"), "Unauthorized");
	    assertTrue(response.jsonPath().getString("message").contains("unexpected error"));
	}
	
	// Create Patient using Admin token
	@Given("Admin creates POST request by entering valid data into the form-data key and value fields with {string}")
	public void admin_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNjAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDEwNDc4MSwiZXhwIjoxNzU0MTMzNTgxfQ.A6JQ4r9zDn5rjbfDrocr39jQul8msY7oKWqOKNDaIYRSCpUkfao5RFIKcZGjp0bcdEMNXEfA6Nm2G5r16muhCw")
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }	
	}

	@When("Admin send POST http request with endpoint to create patient with {string}")
	public void admin_send_post_http_request_with_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		response = request.when().post(patientData.getEndpoint());
	    	}
	    }
	}

	@Then("Admin recieves {int} forbidden after create patient request")
	public void admin_recieves_forbidden_after_create_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("errorMessage").contains("Access is denied"));
	}
	
	// Create patient with valid data using Dietician token successfully
	@Given("Dietician creates POST request by entering valid data with {string}. \\( Mandatory and additional details) into the form-data key and value fields.")
	public void dietician_creates_post_request_by_entering_valid_data_with_mandatory_and_additional_details_into_the_form_data_key_and_value_fields(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MDk3MTg5LCJleHAiOjE3NTQxMjU5ODl9.nV82gp6J5zzpZjeznLd_WOcxSRPANki9TB8ZaaWd9A9ngxSnxlduqoTHj5d2EL6sUh992gf11Ux64mR23GuYTA")
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }
	}

	@When("Dietician send POST http request with endpoint to create patient with {string}")
	public void dietician_send_post_http_request_with_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		response = request.when().post(patientData.getEndpoint());
	    	}
	    }
	}
	
	@Then("Dietician recieves {int} created and with response body. \\(Auto created dietician ID and login password)")
	public void dietician_recieves_created_and_with_response_body_auto_created_dietician_id_and_login_password(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
	}
	
	// Create Patient using Patient token
	@Given("Patient creates POST request by entering valid data into the form-data key and value fields with {string}")
	public void patient_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))
	    	    	.header("Authorization", "Bearer 992gf11Ux64mR23GuYTA")
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }
	}

	@When("Patient send POST http request with endpoint to create patient with {string}")
	public void patient_send_post_http_request_with_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		response = request.when().post(patientData.getEndpoint());
	    	}
	    }
	}

	@Then("Patient recieves {int} forbidden after create patient request")
	public void patient_recieves_forbidden_after_create_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("errorMessage").contains("Access is denied"));
	}
	
	@Given("Dietician creates POST request only by valid mandatory details into the form-data key and value fields with {string}.")
	public void dietician_creates_post_request_only_by_valid_mandatory_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MDk3MTg5LCJleHAiOjE3NTQxMjU5ODl9.nV82gp6J5zzpZjeznLd_WOcxSRPANki9TB8ZaaWd9A9ngxSnxlduqoTHj5d2EL6sUh992gf11Ux64mR23GuYTA")
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson);
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }
	}
	
	@Given("Dietician creates POST request only by valid additional details into the form-data key and value fields with {string}.")
	public void dietician_creates_post_request_only_by_valid_additional_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Not Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MTUwODM0LCJleHAiOjE3NTQxNzk2MzR9.jYjhBgld1fyf0dbagCmAD33Qloakv9H3qZO7ILnJlnoOyc2cHcAkbgvWTO5eDS48Y9Dk15DvKHmwCiFe-Kg2sQ")
	    	    	.contentType(ContentType.MULTIPART)
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }
	}

	@Then("Dietician recieves {int} Bad request after create patient request")
	public void dietician_recieves_bad_request_after_create_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("error").contains("Bad Request"));
		assertTrue(response.jsonPath().getString("message").contains("unexpected error"));
	}
	
	@Given("Dietician creates POST request only by invalid mandatory details into the form-data key and value fields with {string}.")
	public void dietician_creates_post_request_only_by_invalid_mandatory_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))	    	    	
	    	    	.contentType(ContentType.MULTIPART)
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MTUwODM0LCJleHAiOjE3NTQxNzk2MzR9.jYjhBgld1fyf0dbagCmAD33Qloakv9H3qZO7ILnJlnoOyc2cHcAkbgvWTO5eDS48Y9Dk15DvKHmwCiFe-Kg2sQ")
	    	   		.multiPart("patientInfo", patientInfoJson)
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }	    
	}
	
	@Then("Dietician recieves {int} Bad request after create patient request with {string}")
	public void dietician_recieves_bad_request_after_create_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("errorCode").contains("INVALID_REQ_DATA"));
		assertTrue(response.jsonPath().getString("errorMessage").contains(errorMessage));
	}
	
//	@Given("Dietician creates POST request only by invalid additional details into the form-data key and value fields with {string}.")
//	public void dietician_creates_post_request_only_by_invalid_additional_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
//	    ObjectMapper mapper = new ObjectMapper();
//	    
//	    patientDataList = mapper.readValue(
//		        new File("src/test/resources/testData/patientData.json"),
//		        new TypeReference<List<PatientTestData>>() {}
//		    );
//	    
//	    for(PatientTestData patientData : patientDataList) {
//	    	if(patientData.getScenarioName().equals(scenarioName)) {
//	    		PatientPojo patientPojo = patientData.getPatientDataInput();
//	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
//	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
//	    		
//	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
//	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
//	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
//	    		
//	    		File file = new File(configReader.getProperty("pdfPath"));
//	    		String filePath = file.getAbsolutePath();
//	    		System.out.println("Sending file as: " +filePath);
//	    		
//	    	    request = given()
//	    	    	.baseUri(configReader.getProperty("baseURL"))	    	    	
//	    	    	.contentType(ContentType.MULTIPART)
//	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MTUwODM0LCJleHAiOjE3NTQxNzk2MzR9.jYjhBgld1fyf0dbagCmAD33Qloakv9H3qZO7ILnJlnoOyc2cHcAkbgvWTO5eDS48Y9Dk15DvKHmwCiFe-Kg2sQ")
//	    	   		.multiPart("patientInfo", patientInfoJson)
//	    		    .multiPart("vitals", vitalsInfoJson);
//	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
//	    	    
//	    	    System.out.println("POST request sent: Breaking loop");
//	    	    break;
//	    	}
//	    }	    
//	}
	
	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields with {string}.")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))	    	    	
	    	    	.contentType(ContentType.MULTIPART)
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MTUwODM0LCJleHAiOjE3NTQxNzk2MzR9.jYjhBgld1fyf0dbagCmAD33Qloakv9H3qZO7ILnJlnoOyc2cHcAkbgvWTO5eDS48Y9Dk15DvKHmwCiFe-Kg2sQ")
	    	   		.multiPart("patientInfo", patientInfoJson)
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }	    
	}

	@When("Dietician send PUT http request with endpoint to create patient with {string}")
	public void dietician_send_put_http_request_with_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		response = request.when().put(patientData.getEndpoint());
	    	}
	    }
	}

	@Then("Dietician recieves {int} method not allowed after create patient request with {string}")
	public void dietician_recieves_method_not_allowed_after_create_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("error").contains("Method Not Allowed"));
		assertTrue(response.jsonPath().getString("message").contains(errorMessage));
	}
	
	@Given("Dietician creates POST request with valid token by entering valid data into the form-data key and value fields with {string}.")
	public void dietician_creates_post_request_with_valid_token_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))	    	    	
	    	    	.contentType(ContentType.MULTIPART)
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MTUwODM0LCJleHAiOjE3NTQxNzk2MzR9.jYjhBgld1fyf0dbagCmAD33Qloakv9H3qZO7ILnJlnoOyc2cHcAkbgvWTO5eDS48Y9Dk15DvKHmwCiFe-Kg2sQ")
	    	   		.multiPart("patientInfo", patientInfoJson)
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }	    
	}

	@When("Dietician send POST http request with invalid endpoint to create patient with {string}")
	public void dietician_send_post_http_request_with_invalid_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		response = request.when().post(patientData.getEndpoint());
	    	}
	    }
	}

	@Then("Dietician recieves {int} not found after create patient request with {string}")
	public void dietician_recieves_not_found_after_create_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("error").contains("Not Found"));
		assertTrue(response.jsonPath().getString("message").contains(errorMessage));
	}
	
	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields and invalid content type with {string}")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_and_invalid_content_type_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    		File file = new File(configReader.getProperty("pdfPath"));
	    		String filePath = file.getAbsolutePath();
	    		System.out.println("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))	    	    	
	    	    	.contentType(ContentType.URLENC)
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MTUwODM0LCJleHAiOjE3NTQxNzk2MzR9.jYjhBgld1fyf0dbagCmAD33Qloakv9H3qZO7ILnJlnoOyc2cHcAkbgvWTO5eDS48Y9Dk15DvKHmwCiFe-Kg2sQ")
	    	   		.formParam("patientInfo", patientInfoJson)
	    		    .formParam("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }	    
	}

	@Then("Dietician recieves {int} unsupported media type after create patient request with {string}")
	public void dietician_recieves_unsupported_media_type_after_create_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("error").contains("Unsupported Media Type"));
		assertTrue(response.jsonPath().getString("message").contains(errorMessage));
	}
}
