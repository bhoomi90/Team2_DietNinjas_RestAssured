package utilities;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import pojo.MorbidityTestData;
import pojo.TestCaseData;
import pojo.TestCasesWrapper;
import pojo.loginData;
import stepDefinitions.Hooks;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.request;


public class CRUDHelper {

    public static TestCasesWrapper allTestData = JSONDataReader.readAllModules("src/test/resources/testData/jsonData.json");

    public static RequestSpecification getRequestWithNoAuth() {

        RestAssured.baseURI = configReader.getProperty ("baseURL");
        //Setting up the request before sending an API
        RequestSpecification request = RestAssured.given();
        return request;
    }

    public static RequestSpecification getRequestWithToken(String token){
        RestAssured.baseURI = configReader.getProperty ("baseURL");
        RequestSpecification request = RestAssured.given().header("Authorization", "Bearer " + token);
        return request;
    }

    public static void loginWith(String testCaseId) {
        TestCaseData currentLoginTest = JSONDataReader.getTestCaseById(allTestData.getLoginTests(), testCaseId);
        loginData loginInputData = currentLoginTest.getLoginInputdata();

        RequestSpecification request = given()
                .baseUri(Hooks.baseUrl)
                .header("Content-Type", "application/json")
                .body(loginInputData);

        Response response = request.post(currentLoginTest.getEndpoints());

        if (response.getStatusCode() == 200) {

            String role = response.jsonPath().getString("roles[0]");
            String token = response.jsonPath().getString("token");

            switch (role) {
                case "ROLE_DIETICIAN":
                    apiTextContext.dieticianToken = token;
                    break;

                case "ROLE_ADMIN":
                    apiTextContext.authToken = token;
                    break;

                case "ROLE_PATIENT":
                    apiTextContext.patientToken = token;
                    break;

                default:
                    LoggerLoad.warn("Unrecognized role: " + role);
                    break;
            }
        }

    }

    public static MorbidityTestData getMorbidityTestBy(String testCaseId) {
        MorbidityTestData morbidityTestData = JSONDataReader.getMorbidityTestById(allTestData.getMorbidityTests(), testCaseId);
        return morbidityTestData;
    }


}
