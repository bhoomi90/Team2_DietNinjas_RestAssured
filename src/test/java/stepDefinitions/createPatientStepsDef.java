package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

	//List<PatientPojo> patientDataList;
	List<PatientTestData> patientDataList;
	//PatientPojo patientPojo;
	RequestSpecification request;
	Response response;
	
	// Create Patient without Bearer token
//	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields.")
//	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws IOException {
//	    ObjectMapper mapper = new ObjectMapper();
//	    
//	    // Read and convert all JSON entries to List of POJOs
//	    List<PatientPojo> patientDataList = mapper.readValue(
//	        new File("src/test/resources/testData/patientData.json"),
//	        new TypeReference<List<PatientPojo>>() {}
//	    );
//	    
//	    for (PatientPojo patientPojo : patientDataList) {
//	    	if(patientPojo.getTestCaseId().equals("p2")) {
//	    	    // Get first patient
//	    	    //PatientPojo patientPojo = patientDataList.get(0);
//	    	    VitalsPojo vitalsPojo = patientPojo.getVitals();
//	    	    
//	    	    // Convert patient object to JSON string
//	    	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
//	    	    System.out.println("Sending patientInfo as: " + patientInfoJson);
//	    	    
//	    	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
//	    	    System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
//	    	    
//	    	    request = given()
//	    	    		.baseUri(configReader.getProperty("baseURL"))
//	    	 //   		.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MDA0Nzk3LCJleHAiOjE3NTQwMzM1OTd9.NVaq8yBCrGjJvzwRCXZvxcZTeyX-d6FFl5wj56aL-YpqVt1Iy9dOlJtGQNninrf46tOPOp9OlH-7wS7okHhfag")
//	    	    		.contentType(ContentType.MULTIPART)
//	    	    		.multiPart("patientInfo", patientInfoJson)
//	    	    		.multiPart("vitals", vitalsInfoJson);
//	    	    
//	    	    System.out.println("POST request sent: Breaking loop");
//	    	    break;
//	    	}
//	    }
//	    
//	}
//

//
//	@Then("Dietician recieves {int} unauthorized after create patient request")
//	public void dietician_recieves_unauthorized_after_create_patient_request(Integer expectedStatusCode) {
//	    response.then().log().all().statusCode(expectedStatusCode);
//	    assertEquals(response.jsonPath().getString("error"), "Unauthorized");
//	    assertTrue(response.jsonPath().getString("message").contains("unexpected error"));
//	}
//	
//	// Create Patient using Admin token
//	@Given("Admin creates POST request by entering valid data into the form-data key and value fields.")
//	public void admin_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws IOException {
//	    ObjectMapper mapper = new ObjectMapper();
//	    
//	    // Read and convert all JSON entries to List of POJOs
//	    List<PatientPojo> patientDataList = mapper.readValue(
//	        new File("src/test/resources/testData/patientData.json"),
//	        new TypeReference<List<PatientPojo>>() {}
//	    );
//	    
//	    // Get first patient
//	    PatientPojo patientPojo = patientDataList.get(0);
////	    VitalsPojo vitalsPojo = patientPojo.getVitals();
//	    
//	    // Convert patient object to JSON string
//	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
//	    System.out.println("Sending patientInfo as: " + patientInfoJson);
//	    
////	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
////	    System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
//	    
//	    request = given()
//	    		.baseUri(configReader.getProperty("baseURL"))
//	    		.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNjAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDAwNjQ0OSwiZXhwIjoxNzU0MDM1MjQ5fQ.t7BjYLTyaNft2EGx3Yyk4nKZRs7vCnaZNs-bbc3g7ND2QSCnPcPuEdFldqX3bvhQLPPLdMTs69xmnihrqZJqag")
//	    		.contentType(ContentType.MULTIPART)
//	    		.multiPart("patientInfo", patientInfoJson)
//	    		.multiPart("vitals", vitalsInfoJson);
//	
//	}
//
//	@When("Admin send POST http request with endpoint to create patient")
//	public void admin_send_post_http_request_with_endpoint_to_create_patient() {
//		response = request.when().post("/patient");		
//	}
//
//	@Then("Admin recieves {int} forbidden after create patient request")
//	public void admin_recieves_forbidden_after_create_patient_request(Integer expectedStatusCode) {
//		response.then().log().all().statusCode(expectedStatusCode);
//		assertTrue(response.jsonPath().getString("errorMessage").contains("Access is denied"));
//	}
	
	// Create patient with valid data using Dietician token successfully
	@Given("Dietician creates POST request by entering valid data with {string}. \\( Mandatory and additional details) into the form-data key and value fields.")
	public void dietician_creates_post_request_by_entering_valid_data_with_mandatory_and_additional_details_into_the_form_data_key_and_value_fields(String testCaseID) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientDataList = mapper.readValue(
		        new File("src/test/resources/testData/patientData.json"),
		        new TypeReference<List<PatientTestData>>() {}
		    );
	    
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getTestCaseId().equals(testCaseID)) {
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		System.out.println("Sending patientInfo as: " + patientInfoJson);
	    		
	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
	    		System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    		
	    	    request = given()
	    	    	.baseUri(configReader.getProperty("baseURL"))
	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MDk3MTg5LCJleHAiOjE3NTQxMjU5ODl9.nV82gp6J5zzpZjeznLd_WOcxSRPANki9TB8ZaaWd9A9ngxSnxlduqoTHj5d2EL6sUh992gf11Ux64mR23GuYTA")
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    		    .multiPart("vitals", vitalsInfoJson);
	    	    
	    	    System.out.println("POST request sent: Breaking loop");
	    	    break;
	    	}
	    }
	    
	    // Read and convert all JSON entries to List of POJOs
//	    List<PatientPojo> patientDataList = mapper.readValue(
//	        new File("src/test/resources/testData/patientData.json"),
//	        new TypeReference<List<PatientPojo>>() {}
//	    );
	    	    
//	    for (PatientPojo patientPojo : patientDataList) {
//	    	if(patientPojo.getTestCaseId().equals("p1")) {
	    	    
//	    		if (!patientPojo.getDateOfBirth().matches("\\d{4}-\\d{2}-\\d{2}")) {
//	    			System.out.println("I am here");
//	    		    throw new IllegalArgumentException("Invalid date format: " + patientPojo.getDateOfBirth());
//	    		}
	    		
//	    		LocalDate dob = LocalDate.parse(patientPojo.getDateOfBirth(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//	    		patientPojo.setDateOfBirth(dob.toString());
	    		
//	    	    VitalsPojo vitalsPojo = patientPojo.getVitals();
	    	    
	    	    // Convert patient object to JSON string
//	    	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
//	    	    ObjectNode root = (ObjectNode) mapper.readTree(patientInfoJson);
//	    	    root.remove("testCaseId");
//	    	    patientInfoJson = mapper.writeValueAsString(root);
//	    	    System.out.println("Sending patientInfo as: " + patientInfoJson);
	    	    
//	    	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
//	    	    System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
	    	    
//	    	    request = given()
//	    	    		.baseUri(configReader.getProperty("baseURL"))
//	    	    		.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MDY3ODU3LCJleHAiOjE3NTQwOTY2NTd9.IQlO6nKQQJl7Jez_WUgw0_BM5jW_ygcQ3ok9IADl2VHiWGLPdNg20iZZzpKHbbpQwqEsLassGV718QLt5eDUBw")
//	    	    		.contentType(ContentType.MULTIPART)
//	    	    		.multiPart("patientInfo", patientInfoJson);
//	    	    		//.multiPart("vitals", vitalsInfoJson, "application/json");
//	    	    
//	    	    System.out.println("POST request sent: Breaking loop");
//	    	    break;
//	    	}
//	    }
	}

	@When("Dietician send POST http request with endpoint to create patient with {string}")
	public void dietician_send_post_http_request_with_endpoint_to_create_patient_with(String testCaseID) {
	    for(PatientTestData patientData : patientDataList) {
	    	if(patientData.getTestCaseId().equals(testCaseID)) {
	    		response = request.when().post(patientData.getEndpoint());
	    	}
	    }
	}
	
	@Then("Dietician recieves {int} created and with response body. \\(Auto created dietician ID and login password)")
	public void dietician_recieves_created_and_with_response_body_auto_created_dietician_id_and_login_password(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
	}
	
	// Create Patient using Patient token
//	@Given("Patient creates POST request by entering valid data into the form-data key and value fields.")
//	public void patient_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields() throws IOException {
//	    ObjectMapper mapper = new ObjectMapper();
//	    
//	    // Read and convert all JSON entries to List of POJOs
//	    List<PatientPojo> patientDataList = mapper.readValue(
//	        new File("src/test/resources/testData/patientData.json"),
//	        new TypeReference<List<PatientPojo>>() {}
//	    );
//	    
//	    // Get first patient
//	    PatientPojo patientPojo = patientDataList.get(0);
//	    VitalsPojo vitalsPojo = patientPojo.getVitals();
//	    
//	    // Convert patient object to JSON string
//	    String patientInfoJson = mapper.writeValueAsString(patientPojo);
//	    System.out.println("Sending patientInfo as: " + patientInfoJson);
//	    
//	    String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
//	    System.out.println("Sending vitalsInfo as: " + vitalsInfoJson);
//	    
//	    request = given()
//	    		.baseUri(configReader.getProperty("baseURL"))
//	    		.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtNjAxQGdtYWlsLmNvbSIsImlhdCI6MTc1NDAwNjQ0OSwiZXhwIjoxNzU0MDM1MjQ5fQ.t7BjYLTyaNft2EGx3Yyk4nKZRs7vCnaZNs-bbc3g7ND2QSCnPcPuEdFldqX3bvhQLPPLdMTs69xmnihrqZJqag")
//	    		.contentType(ContentType.MULTIPART)
//	    		.multiPart("patientInfo", patientInfoJson)
//	    		.multiPart("vitals", vitalsInfoJson);
//	}
//
//	@When("Patient send POST http request with endpoint to create patient")
//	public void patient_send_post_http_request_with_endpoint_to_create_patient() {
//		response = request.when().post("/patient");
//	}
//
//	@Then("Patient recieves {int} forbidden after create patient request")
//	public void patient_recieves_forbidden_after_create_patient_request(Integer expectedStatusCode) {
//		response.then().log().all().statusCode(expectedStatusCode);
//		assertTrue(response.jsonPath().getString("errorMessage").contains("Access is denied"));
//	}
}
