package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class morbidityStepsDef {
    @Given("Dietician create a GET request for morbidity with no auth")
    public void dieticianCreateAValidGETRequestForMorbidityWithNoAuth() {

    }


    @When("Dietician send GET http request with endpoint for morbidity")
    public void dieticianSendGETHttpRequestWithEndpointForMorbidity() {

    }


    @Given("Patient create a valid GET request for morbidity with patient token")
    public void patientCreateAValidGETRequestForMorbidityWithPatientToken() {


    }

    @When("Patient send GET http request with endpoint for morbidity")
    public void patientSendGETHttpRequestWithEndpointForMorbidity() {


    }

    @Then("Patient receives with status code <statusCode>")
    public void patientReceivesWithStatusCodeStatusCode() {

    }

    @Given("Admin create a valid GET request for morbidity with admin token")
    public void adminCreateAValidGETRequestForMorbidityWithAdminToken() {
    }

    @When("Admin send GET http request with endpoint for morbidity")
    public void adminSendGETHttpRequestWithEndpointForMorbidity() {
    }

    @Then("Admin receives with status code <statusCode> with details of the patient id")
    public void adminReceivesWithStatusCodeStatusCodeWithDetailsOfThePatientId() {
    }

    @Given("Admin create a valid POST request for morbidity with admin token")
    public void adminCreateAValidPOSTRequestForMorbidityWithAdminToken() {
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


    @Given("Dietician create a valid GET request for morbidity with dietician token")
    public void dieticianCreateAValidGETRequestForMorbidityWithDieticianToken() {
    }

    @Then("Dietician receives with status code {int}")
    public void dieticianReceivesWithStatusCode(int expectedStatusCode) {

    }
}
