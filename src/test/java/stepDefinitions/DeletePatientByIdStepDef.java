package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utilities.CRUDHelper;
import utilities.apiTextContext;

import static io.restassured.RestAssured.given;

public class DeletePatientByIdStepDef {
    RequestSpecification request;
    Response response;
    int patientId;

    @Given("Dietician create DELETE request with no auth")
    public void dieticianCreateDELETERequestWithNoAuth() {
        //receiving a request
        request = CRUDHelper.getRequestWithNoAuth();
    }

    @When("Dietician send DELETE http request with endpoint")
    public void dieticianSendDELETEHttpRequestWithEndpoint() {
        response = request.delete("/patient/" + patientId);
    }

    @Then("Dietician receives status code {int}")
    public void dieticianReceivesStatusCode(int expectedStatusCode) {
       int  actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);


    }

    @And("Admin create a request for delete with admin token")
    public void adminCreateARequestForDeleteWithAdminToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.authToken);

    }

    @When("Admin send DELETE http request with endpoint for delete patient")
    public void adminSendDELETEHttpRequestWithEndpointForDeletePatient() {
        response = request.delete("/patient/" + patientId);

    }

    @Then("Admin receives status code {int}")
    public void adminReceivesStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);

    }

    @And("Patient create a request for delete with patient token")
    public void patientCreateARequestForDeleteWithPatientToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.patientToken);
    }

    @When("Patient send DELETE http request with endpoint for delete patient")
    public void patientSendDELETEHttpRequestWithEndpointForDeletePatient() {
        response = request.delete("/patient/" + patientId);
    }

    @Then("Patient receives status code {int}")
    public void patientReceivesStatusCodeStatusCode(int expectedStatusCode) {
        int actualStatusCode = response.statusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Given("Login as a user with patient login information as in {string} for deletion")
    public void loginAsAUserWithPatientLoginInformationAsInForDeletion(String testCaseId) {
        CRUDHelper.loginWith(testCaseId);
    }

    @Given("Login as a user with dietician login information as in {string} for deletion")
    public void loginAsAUserWithDieticianLoginInformationAsInForDeletion(String testCaseId) {
        CRUDHelper.loginWith(testCaseId);
    }

    @And("Dietician create a request for deletion with dietician token")
    public void dieticianCreateARequestForDeletionWithDieticianToken() {
        request = CRUDHelper.getRequestWithToken(apiTextContext.dieticianToken);
    }

    @When("Dietician send DELETE http request with endpoint for delete patient")
    public void dieticianSendDELETEHttpRequestWithEndpointForDeletePatient() {
        response = request.delete("/patient" + patientId);
    }

    @When("Dietician send POST http request with endpoint for delete patient")
    public void dieticianSendPOSTHttpRequestWithEndpointForDeletePatient() {
        response = request.post("/patient" + patientId);
    }

    @When("Dietician send DELETE http request with endpoint for delete patient by invalid patient id")
    public void dieticianSendDELETEHttpRequestWithEndpointForDeletePatientByInvalidPatientId() {
        response = request.delete("/patient" + patientId);
    }

    @When("Dietician send DELETE http request with endpoint for delete patient by invalid endpoint")
    public void dieticianSendDELETEHttpRequestWithEndpointForDeletePatientByInvalidEndpoint() {
        response = request.delete("/patie" + patientId);
    }

    @Given("Login as a user with dietician login information as in {string} for creating patient")
    public void loginAsAUserWithDieticianLoginInformationAsInForCreatingPatient(String testCaseId) {
        CRUDHelper.loginWith(testCaseId);
    }

    @And("Create a new patient using {string}")
    public void createANewPatientUsing(String testCaseId) {
        patientId = CRUDHelper.createNewPatient(testCaseId);
    }

    @After
    public void deletePatient() {
        CRUDHelper.getRequestWithToken(apiTextContext.dieticianToken).delete("/patient/" + patientId);
    }
}
