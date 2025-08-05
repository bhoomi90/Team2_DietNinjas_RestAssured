package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

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
import utilities.apiTextContext;

public class CreatePatientStepDef {

	List<PatientTestData> patientTestCase;
	PatientTestData patientData;
    public static String patientId;       //<-- save id 
    public static String patientEmail;    // -------- save email
	RequestSpecification request;
	Response response;
	static String dieticianToken;// = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0NDAzMDcyLCJleHAiOjE3NTQ0MzE4NzJ9.vY-qQ_VoTaMdR62va5ouWoUKwp5kMEO0jGNuBLHQUMnp_CTPMpKzamUb0JQ5P_e9QNIC0LyGUPxoufZcJmPVPw";
	static String adminToken ;//= "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJUZWFtMTEwQGdtYWlsLmNvbSIsImlhdCI6MTc1NDQwMzYyMiwiZXhwIjoxNzU0NDMyNDIyfQ.ICJzj64s-_yrDB3Mh0jlNSEyPW3CgKycsgRYpT1Ui4BKKB47ZTTvISPYho_1pvs9ShhRr_L59SqII0KAgeZRNw";
	static String patientToken;
	
	// Set bearer token
	@Given("Set Bearer Token with {string}")
	public void set_bearer_token_with(String tokenName) {
		
		if(tokenName.equals("dietician_token")) {
			dieticianToken = apiTextContext.dieticianToken;
			LoggerLoad.info("Dietician token is: " +dieticianToken);
			
	        if (dieticianToken == null || dieticianToken.isEmpty()) {
	            throw new RuntimeException("Dietician_token not set from login. Make sure login scenario for dietician runs before this.");
	        }
		}
		else if(tokenName.equals("admin_token")) {
			adminToken = apiTextContext.authToken;
			LoggerLoad.info("Admin token is: " +adminToken);
			
	        if (adminToken == null || adminToken.isEmpty()) {
	            throw new RuntimeException("Admin_token not set from login. Make sure login scenario for dietician runs before this.");
	        }
		}
		else if(tokenName.equals("patient_token")) {
			patientToken = apiTextContext.patientToken;
			LoggerLoad.info("Patient token is: " +patientToken);
			
	        if (patientToken == null || patientToken.isEmpty()) {
	            throw new RuntimeException("Patient_token not set from login. Make sure login scenario for dietician runs before this.");
	        }
		}
	}
	
	// Create Patient without Bearer token
	@Given("Dietician creates POST request by entering valid data into the form-data key and value fields with {string}.")
	public void dietician_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    patientTestCase = Hooks.allTestData.getPatientTests();
	
	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
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
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    	   		.multiPart("file", file, "application/pdf")
	    		    .multiPart("vitals", vitalsInfoJson);
	    	      
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
		patientTestCase = Hooks.allTestData.getPatientTests();	    
	    
	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
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
	    	    	.header("Authorization", "Bearer " +adminToken)
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    	   		.multiPart("file", file, "application/pdf")
	    		    .multiPart("vitals", vitalsInfoJson);
	    	    
	}

	@When("Admin send POST http request with endpoint to create patient with {string}")
	public void admin_send_post_http_request_with_endpoint_to_create_patient_with(String scenarioName) {
	    response = request.when().post(patientData.getEndpoint());
	}

	@Then("Admin recieves {int} forbidden after create patient request")
	public void admin_recieves_forbidden_after_create_patient_request(Integer expectedStatusCode) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("errorMessage").contains("Access is denied"));
	}
	
	@Given("Dietician creates POST request by entering valid data with {string}. \\( Mandatory and additional details) into the form-data key and value fields.")
	public void dietician_creates_post_request_by_entering_valid_data_with_mandatory_and_additional_details_into_the_form_data_key_and_value_fields(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    
	    patientTestCase = Hooks.allTestData.getPatientTests();
//	    patientDataList = mapper.readValue(
//		        new File("src/test/resources/testData/patientData.json"),
//		        new TypeReference<List<PatientTestData>>() {}
//		    );
	    
	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
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

	@When("Dietician send POST http request with endpoint to create patient with {string}")
	public void dietician_send_post_http_request_with_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientTestCase) {
	    	if(patientData.getScenarioName().equals(scenarioName)) {
	    		response = request.when().post(patientData.getEndpoint());
	    	}
	    }
	}
	
	@Then("Dietician recieves {int} created and with response body. \\(Auto created dietician ID and login password)")
	public void dietician_recieves_created_and_with_response_body_auto_created_dietician_id_and_login_password(Integer expectedStatusCode) {
		
		response.then().log().all().statusCode(expectedStatusCode);
		
		if(response.statusCode()==expectedStatusCode) {
			LoggerLoad.info("Patient is created successfully");
		}

		if(expectedStatusCode == 201) {
	       	patientId = response.jsonPath().getString("patientId");
	    	LoggerLoad.info("PatientId saved:"+ patientId);
	    	
	    	patientEmail = response.jsonPath().getString("Email");
	    	LoggerLoad.info("PatientEmail saved: " +patientEmail);
	    	
			assertEquals(response.jsonPath().getString("FirstName"), patientData.getPatientDataInput().getFirstname());
			assertEquals(response.jsonPath().getString("LastName"), patientData.getPatientDataInput().getLastname());
			assertEquals(response.jsonPath().getString("Email"), patientData.getPatientDataInput().getEmail());
			assertEquals(response.jsonPath().getString("Allergy"), patientData.getPatientDataInput().getAllergy());
			assertEquals(response.jsonPath().getString("ContactNumber"), patientData.getPatientDataInput().getContactNumber());
			assertEquals(response.jsonPath().getString("DateOfBirth"), patientData.getPatientDataInput().getDateOfBirth());
			assertEquals(response.jsonPath().getString("FoodPreference"), patientData.getPatientDataInput().getFoodPreference());
			assertEquals(response.jsonPath().getString("CuisineCategory"), patientData.getPatientDataInput().getCuisineCategory());
			if(patientData.getScenarioName().equals("Create patient")) {
			response.then().body(matchesJsonSchemaInClasspath("schema/createPatientSchema.json"));
			
			assertTrue(response.getBody().asString().contains("patientId"), "patientId is not present in response");
			assertTrue(response.getBody().asString().contains("FileMorbidity"), "FileMorbidity is not present in response");
			assertTrue(response.getBody().asString().contains("FileMorbidityCondition"), "FileMorbidityCondition is not present in response");
			assertTrue(response.getBody().asString().contains("Vitals"), "Vitals is not present in response");
			assertTrue(response.getBody().asString().contains("DieticianId"), "DieticianId is not present in response");
			assertTrue(response.getBody().asString().contains("LastVisitDate"), "LastVisitDate is not present in response");			
			
			assertNotNull(response.jsonPath().getString("patientId"), "patientId must not be null");
			assertNotNull(response.jsonPath().getString("DieticianId"), "DieticianId must not be null");
			
			Map<String, Object> fileMorbidityMap = response.jsonPath().getMap("FileMorbidity");
			String fileId = fileMorbidityMap.keySet().iterator().next();
			LoggerLoad.info("fileID: " + fileId);
			
			String t3 = response.jsonPath().getString("FileMorbidity." + fileId + ".T3");
			String t4 = response.jsonPath().getString("FileMorbidity." + fileId + ".T4");
			String tsh = response.jsonPath().getString("FileMorbidity." + fileId + ".TSH");
			LoggerLoad.info("T3: " +t3+ " T4: " +t4+ " TSH: " +tsh);
			
			String fileMorbidityCondition = response.jsonPath().getString("FileMorbidityCondition." + fileId);
			LoggerLoad.info("fileMorbidityCondition : " +fileMorbidityCondition);
			
			String t3Numeric = t3.split(" ")[0]; // "6.3"
			float t3Value = Float.parseFloat(t3Numeric);
			LoggerLoad.info("t3Numeric: " +t3Numeric+ " t3Value: " +t3Value);
			
			String t4Numeric = t4.split(" ")[0]; 
			float t4Value = Float.parseFloat(t4Numeric);
			LoggerLoad.info("t3Numeric: " +t4Numeric+ " t3Value: " +t4Value);
			
			String tshNumeric = tsh.split(" ")[0]; 
			float tshValue = Float.parseFloat(tshNumeric);
			LoggerLoad.info("t3Numeric: " +tshNumeric+ " t3Value: " +tshValue);
			
			if(t3Value>1.8 && t4Value>12 && tshValue<0.55) {
				assertTrue(fileMorbidityCondition.contains("Hyperthyroidism"), "Should have Hyperthyroidism based on condition");
			}
			
			if(tshValue>4.78 && t4Value<5) {
				assertTrue(fileMorbidityCondition.contains("Hypothyroidism"), "Should have Hypothyroidism based on condition");
			}
		}
		}
	}
	
	// Create Patient using Patient token
	@Given("Patient creates POST request by entering valid data into the form-data key and value fields with {string}")
	public void patient_creates_post_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    patientTestCase = Hooks.allTestData.getPatientTests();

	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
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

	@When("Patient send POST http request with endpoint to create patient with {string}")
	public void patient_send_post_http_request_with_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientTestCase) {
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
	    patientTestCase = Hooks.allTestData.getPatientTests();

	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
				break;
			}
		}

	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
	    		
	    	    request = given()
	    	    	.baseUri(Hooks.baseUrl)
	    	    	.header("Authorization", "Bearer "+dieticianToken)
	    	    	.contentType(ContentType.MULTIPART)
	    	   		.multiPart("patientInfo", patientInfoJson);

	}
	
	@Given("Dietician creates POST request only by valid additional details into the form-data key and value fields with {string}.")
	public void dietician_creates_post_request_only_by_valid_additional_details_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    patientTestCase = Hooks.allTestData.getPatientTests();

	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
				break;
			}
		}
	    		PatientPojo patientPojo = patientData.getPatientDataInput();
	    		String patientInfoJson = mapper.writeValueAsString(patientPojo);
	    		LoggerLoad.info("Not Sending patientInfo as: " + patientInfoJson);
	    		
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
	    	    	.multiPart("file", file, "application/pdf")
	    		    .multiPart("vitals", vitalsInfoJson);

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
	    patientTestCase = Hooks.allTestData.getPatientTests();

	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
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
	    		
	    		LoggerLoad.info("Sending file as: " +filePath);
	    		
	    	    request = given()
	    	    	.baseUri(Hooks.baseUrl)	    	    	
	    	    	.contentType(ContentType.MULTIPART)
	    	    	.header("Authorization", "Bearer " +dieticianToken)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    	   		.multiPart("file", file, "application/pdf")
	    		    .multiPart("vitals", vitalsInfoJson);
    
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
//	    		LoggerLoad.info("Sending patientInfo as: " + patientInfoJson);
//	    		
//				String filePath = patientData.getFile();
//				LoggerLoad.info("Sending file as: " +filePath);
//				File file = new File(filePath);
//	
//	    		VitalsPojo vitalsPojo = patientData.getVitalsDataInput();
//	    		String vitalsInfoJson = mapper.writeValueAsString(vitalsPojo);
//	    		LoggerLoad.info("Sending vitalsInfo as: " + vitalsInfoJson);	    		
//	    		
//	    	    request = given()
//	    	    	.baseUri(configReader.getProperty("baseURL"))	    	    	
//	    	    	.contentType(ContentType.MULTIPART)
//	    	    	.header("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ2YWhhMTBAZ21haWwuY29tIiwiaWF0IjoxNzU0MTUwODM0LCJleHAiOjE3NTQxNzk2MzR9.jYjhBgld1fyf0dbagCmAD33Qloakv9H3qZO7ILnJlnoOyc2cHcAkbgvWTO5eDS48Y9Dk15DvKHmwCiFe-Kg2sQ")
//	    	   		.multiPart("patientInfo", patientInfoJson)
//					.multiPart("file", file, "application/pdf")
//	    		    .multiPart("vitals", vitalsInfoJson);
//	    	    
//	    	    LoggerLoad.info("POST request sent: Breaking loop");
//	    	    break;
//	    	}
//	    }	    
//	}
	
	@Given("Dietician creates PUT request by entering valid data into the form-data key and value fields with {string}.")
	public void dietician_creates_put_request_by_entering_valid_data_into_the_form_data_key_and_value_fields_with(String scenarioName) throws IOException {
	    ObjectMapper mapper = new ObjectMapper();
	    patientTestCase = Hooks.allTestData.getPatientTests();

	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
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
	    	    	.contentType(ContentType.MULTIPART)
	    	    	.header("Authorization", "Bearer " +dieticianToken)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    	   		.multiPart("file", file, "application/pdf")
	    		    .multiPart("vitals", vitalsInfoJson);
    
	}

	@When("Dietician send PUT http request with endpoint to create patient with {string}")
	public void dietician_send_put_http_request_with_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientTestCase) {
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
	    patientTestCase = Hooks.allTestData.getPatientTests();

	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
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
	    	    	.contentType(ContentType.MULTIPART)
	    	    	.header("Authorization", "Bearer " +dieticianToken)
	    	   		.multiPart("patientInfo", patientInfoJson)
	    	   		.multiPart("file", file, "application/pdf")
	    		    .multiPart("vitals", vitalsInfoJson);
	    		    //.multiPart("file", new File(configReader.getProperty("pdfPath")));
    
	}

	@When("Dietician send POST http request with invalid endpoint to create patient with {string}")
	public void dietician_send_post_http_request_with_invalid_endpoint_to_create_patient_with(String scenarioName) {
	    for(PatientTestData patientData : patientTestCase) {
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
	    patientTestCase = Hooks.allTestData.getPatientTests();
	
	    for(PatientTestData patientTest : patientTestCase) {
	    	if(patientTest.getScenarioName().equals(scenarioName)) {
				patientData = patientTest;
				LoggerLoad.info("Saved patient data: Breaking loop");
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
	    	    	.contentType(ContentType.URLENC)
	    	    	.header("Authorization", "Bearer " +dieticianToken)
	    	   		.formParam("patientInfo", patientInfoJson)
	    	   		//.formParam("file", file, "application/pdf")
	    		    .formParam("vitals", vitalsInfoJson);
    
	}

	@Then("Dietician recieves {int} unsupported media type after create patient request with {string}")
	public void dietician_recieves_unsupported_media_type_after_create_patient_request_with(Integer expectedStatusCode, String errorMessage) {
		response.then().log().all().statusCode(expectedStatusCode);
		assertTrue(response.jsonPath().getString("error").contains("Unsupported Media Type"));
		assertTrue(response.jsonPath().getString("message").contains(errorMessage));
	}
}