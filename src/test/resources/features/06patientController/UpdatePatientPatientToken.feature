@UpdatePatientWithToken
Feature: Put Operation [update patient]

Background: Set patient bearer token

@UpdatePatientWithToken_001
Scenario: Check patient able to update patient with valid data and patient token
Given Patient creates PUT request by entering valid data into the form-data key and value fields.
When Patient send PUT http request with endpoint to update patient
Then Patient recieves 403 forbidden after update patient request
