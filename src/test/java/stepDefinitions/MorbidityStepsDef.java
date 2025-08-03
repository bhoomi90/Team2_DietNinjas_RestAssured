package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utilities.CRUDHelper;
import utilities.apiTextContext;

public class MorbidityStepsDef {
    RequestSpecification request;
    Response response;

    @Given("Dietician create a request for morbidity with no auth")
    public void dieticianCreateARequestForMorbidityWithNoAuth() {
      request = CRUDHelper.getRequestWithNoAuth();
    }


    @When("Dietician send GET http request with endpoint for morbidity")
    public void dieticianSendGETHttpRequestWithEndpointForMorbidity() {
        response = request.get("/morbidity");
    }


    @And("Patient create a request for morbidity with patient token")
    public void patientCreateARequestForMorbidityWithPatientToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.patientToken);
    }

    @When("Patient send GET http request with endpoint for morbidity")
    public void patientSendGETHttpRequestWithEndpointForMorbidity() {
//      Send the GET request
        response = request.get("/morbidity");
//      Store the response in the object

    }


    @And("Admin create a request for morbidity with admin token")
    public void adminCreateARequestForMorbidityWithAdminToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.authToken);
    }

    @When("Admin send GET http request with endpoint for morbidity")
    public void adminSendGETHttpRequestWithEndpointForMorbidity() {
//      Send the GET request
        response = request.get("/morbidity");
    }




    @When("Admin send POST http request with endpoint for morbidity")
    public void adminSendPOSTHttpRequestWithEndpointForMorbidity() {
        //      Send the POST request
        response = request.post("/morbidity");



    }

    @Then("Admin receives with status code {int}")
    public void adminReceivesWithStatusCodeStatusCode(int expectedStatusCode) {
//        From response read the status code
          int actualStatusCode = response.getStatusCode();
//        Assert the status code to be 405
          Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @When("Admin send GET http request with invalid endpoint for morbidity")
    public void adminSendGETHttpRequestWithInvalidEndpointForMorbidity() {
        //      Send the GET request
        response = request.get("/morbidit");
    }


    @Given("Dietician create a request for morbidity with dietician token")
    public void dieticianCreateRequestForMorbidityWithDieticianToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.dieticianToken);

    }

    @Then("Dietician receives with status code {int}")
    public void dieticianReceivesWithStatusCode(int expectedStatusCode) {
       int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @When("Dietician send POST http request with endpoint for morbidity")
    public void dieticianSendPOSTHttpRequestWithEndpointForMorbidity() {
        //      Send the POST request
        response = request.post("/morbidity");

    }

    @Then("Admin receives with status code {int} with details of the patient id")
    public void adminReceivesWithStatusCodeWithDetailsOfThePatientId(int expectedStatusCode) {
        //  From response read the status code
        int actualStatusCode = response.getStatusCode();
        //  Assert the status code to be 200
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @Then("Patient receives with status code {int}")
    public void patientReceivesWithStatusCodeStatusCode(int expectedStatusCode) {
//        From response read the status code
          int actualStatusCode = response.getStatusCode();
//        Assert the status code to be 403
          Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Given("Login as a user with patient login information as in {string}")
    public void loginAsAUserWithPatientLoginInformationAsIn(String testCaseId) {
        CRUDHelper.loginWith(testCaseId);
    }

    @Given("Login as a user with admin login information as in {string}")
    public void loginAsAUserWithAdminLoginInformationAsIn(String testCaseId) {
        CRUDHelper.loginWith(testCaseId);

    }

    @Given("Login as a user with dietician login information as in {string}")
    public void loginAsAUserWithDieticianLoginInformationAsIn(String testCaseId) {
        CRUDHelper.loginWith(testCaseId);

    }

    @Then("Dietician receives all morbidities with status code {int}")
    public void dieticianReceivesAllMorbiditiesWithStatusCodeStatusCode(int expectedStatusCode) {
//        From response read the status code
        int actualStatusCode = response.getStatusCode();
//        Assert the status code to be 200
        Assert.assertEquals(actualStatusCode, expectedStatusCode);


    }

    @When("Dietician send GET http request with invalid endpoint for morbidity")
    public void dieticianSendGETHttpRequestWithInvalidEndpointForMorbidity() {
        //    Send the GET request
        response = request.get("/morbidit");

    }
}
