@ExistingPatientWithToken
Feature: Put Operation [Add New Reports with/without Vitals for existing Patient]

Background: Set patient bearer token

@ExistingPatientWithToken_001
Scenario Outline: Check patient able to add new reports for existing patient with valid data and patient token
Given Patient creates PUT request by entering valid data into the form-data key and value fields and valid patient ID with "<scenarioName>"
When Patient send PUT http request with endpoint for existing patient with "<scenarioName>"
Then Patient recieves 403 forbidden for existing patient

Examples:
		| scenarioName                              |
		| Create patient with invalid token         |