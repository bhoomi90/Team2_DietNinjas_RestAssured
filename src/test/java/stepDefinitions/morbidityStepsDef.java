package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utilities.CRUDHelper;

public class morbidityStepsDef {
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


    }

    @When("Patient send GET http request with endpoint for morbidity")
    public void patientSendGETHttpRequestWithEndpointForMorbidity() {
//      Send the GET request
        response = request.get("/morbidity");
//      Store the response in the object

    }


    @Given("Admin create a request for morbidity with admin token")
    public void adminCreateARequestForMorbidityWithAdminToken() {
    }

    @When("Admin send GET http request with endpoint for morbidity")
    public void adminSendGETHttpRequestWithEndpointForMorbidity() {
    }




    @When("Admin send POST http request with endpoint for morbidity")
    public void adminSendPOSTHttpRequestWithEndpointForMorbidity() {


    }

    @Then("Admin receives with status code <statusCode>")
    public void adminReceivesWithStatusCodeStatusCode() {
    }

    @When("Admin send GET http request with invalid endpoint for morbidity")
    public void adminSendGETHttpRequestWithInvalidEndpointForMorbidity() {
    }


    @Given("Dietician create a request for morbidity with dietician token")
    public void dieticianCreateRequestForMorbidityWithDieticianToken() {
    }

    @Then("Dietician receives with status code {int}")
    public void dieticianReceivesWithStatusCode(int expectedStatusCode) {
       int actualStatusCode = response.getStatusCode();
        Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @When("Dietician send POST http request with endpoint for morbidity")
    public void dieticianSendPOSTHttpRequestWithEndpointForMorbidity() {
    }


    @Then("Dietician receives with status code {int}")
    public void dieticianReceivesWithStatusCodeStatusCode() {
    }


    @Then("Admin receives with status code {int} with details of the patient id")
    public void adminReceivesWithStatusCodeStatusCodeWithDetailsOfThePatientId() {

    }

    @Then("Patient receives with status code {int}")
    public void patientReceivesWithStatusCodeStatusCode(int expectedStatusCode) {
//        From response read the status code
          int actualStatusCode = response.getStatusCode();
//        Assert the status code to be 403
          Assert.assertEquals(actualStatusCode, expectedStatusCode);
    }

    @Given("Login as a user with username {string} and password {string}")
    public void loginAsAUserWithUsernameAndPassword() {

    }
}
