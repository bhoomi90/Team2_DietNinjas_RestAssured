@ExistingPatientWithAdmin
Feature: Put Operation [Add New Reports with/without Vitals for existing Patient]

Background: Set admin bearer token

@ExistingPatientWithAdmin_001
Scenario Outline: Check admin able to add new reports for existing patient with valid data and admin token
Given Admin creates PUT request by entering valid data into the form-data key and value fields and valid patient ID for existing patient "<scenarioName>"
When Admin send PUT http request with endpoint to update patient with "<scenarioName>"
Then Admin recieves 403 forbidden after patient exists

			Examples:
		| scenarioName                                |
		|ExistingPatientWithInvalidToken_001         |