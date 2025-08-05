package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import pojo.MorbidityTestData;
import utilities.CRUDHelper;
import utilities.apiTextContext;

public class MorbidityByTestNameStepDef {
    RequestSpecification request;
    Response response;

 @Given("Dietician create a request for morbidity by test with no auth")
    public void dieticianCreateARequestForMorbidityByTestWithNoAuth (){
        request = CRUDHelper.getRequestWithNoAuth();
    }

    @When("Dietician send GET http request with endpoint for morbidity by test {string}")
    public void dieticianSendGETHttpRequestWithEndpointForMorbidityByTest(String testCaseId) {
        response = request.get("/morbidity/" + CRUDHelper.getMorbidityTestBy(testCaseId).getMorbidityTestName());
    }

    @Then("Dietician receives with status code {int} for morbidity by test name")
    public void dieticianReceivesWithStatusCodeForMorbidityByTestName(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
//        Assert the status code
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @Given("Login as a user with patient login information as in {string} for morbidity by test name")
    public void loginAsAUserWithPatientLoginInformationAsInForMorbidityByTestName(String loginTestCaseId) {
        CRUDHelper.loginWith(loginTestCaseId);
    }

    @When("Patient send GET http request with endpoint for morbidity by test {string}")
    public void patientSendGETHttpRequestWithEndpointForMorbidityByTest(String testCaseId) {
        response = request.get("/morbidity/" + CRUDHelper.getMorbidityTestBy(testCaseId).getMorbidityTestName());

    }


    @Then("Patient receives with status code {int} for morbidity by test name")
    public void patientReceivesWithStatusCodeStatusCodeForMorbidityByTestName(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
//        Assert the status code to be 403
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @Given("Login as a user with admin login information as in {string} for morbidity by test name")
    public void loginAsAUserWithAdminLoginInformationAsInForMorbidityByTestName(String loginTestCaseId) {
        CRUDHelper.loginWith(loginTestCaseId);

    }

    @When("Admin send GET http request with endpoint for morbidity by test {string}")
    public void adminSendGETHttpRequestWithEndpointForMorbidityByTest(String testCaseId) {
        response = request.get("/morbidity/" + CRUDHelper.getMorbidityTestBy(testCaseId).getMorbidityTestName());

    }

    @Then("Admin receives with status code {int} with details of the patient id for morbidity by test name")
    public void adminReceivesWithStatusCodeStatusCodeWithDetailsOfThePatientIdForMorbidityByTestName(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
//      Assert the status code
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @And("Patient create a request for morbidity by test with patient token")
    public void patientCreateARequestForMorbidityByTestWithPatientToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.patientToken);
    }


    @And("Admin create a request for morbidity by test with admin token")
    public void adminCreateARequestForMorbidityByTestWithAdminToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.authToken);

    }

    @When("Admin send POST http request with endpoint for morbidity by test {string}")
    public void adminSendPOSTHttpRequestWithEndpointForMorbidityByTest(String testCaseId) {
        response = request.post("/morbidity/" + CRUDHelper.getMorbidityTestBy(testCaseId).getMorbidityTestName());

    }

    @When("Admin send GET http request with invalid endpoint for morbidity by test {string}")
    public void adminSendGETHttpRequestWithInvalidEndpointForMorbidityByTest(String testCaseId) {
        //      Send the GET request
        response = request.get("/morbidit" + CRUDHelper.getMorbidityTestBy(testCaseId).getMorbidityTestName());

    }

    @Then("Admin receives with status code {int} for morbidity by test name")
    public void adminReceivesWithStatusCodeStatusCodeForMorbidityByTestName(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
//      Assert the status code
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @Then("Dietician receives all morbidities by test name with status code {int}")
    public void dieticianReceivesAllMorbiditiesByTestNameWithStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
//      Assert the status code
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @And("Dietician create a request for morbidity by test with dietician token")
    public void dieticianCreateARequestForMorbidityByTestWithDieticianToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.dieticianToken);

    }

    @Given("Login as a user with dietician login information as in {string} for morbidity by test name")
    public void loginAsAUserWithDieticianLoginInformationAsInForMorbidityByTestName(String loginTestCaseId)  {
        CRUDHelper.loginWith(loginTestCaseId);

    }

//    @Then("Dietician receives with status code {int}")
//    public void dieticianReceivesWithStatusCode(int expectedStatusCode) {
//        int actualStatusCode = response.getStatusCode();
////      Assert the status code
//        Assert.assertEquals(actualStatusCode, expectedStatusCode);
//
//    }

    @When("Dietician send POST http request with endpoint for morbidity by test {string}")
    public void dieticianSendPOSTHttpRequestWithEndpointForMorbidityByTest(String testCaseId) {
        response = request.post("/morbidity" + CRUDHelper.getMorbidityTestBy(testCaseId).getMorbidityTestName());

    }

    @When("Dietician send GET http request with invalid endpoint for morbidity by test {string}")
    public void dieticianSendGETHttpRequestWithInvalidEndpointForMorbidityByTest(String testCaseId) {
        response = request.post("/morbidi" + CRUDHelper.getMorbidityTestBy(testCaseId).getMorbidityTestName());

    }
}
