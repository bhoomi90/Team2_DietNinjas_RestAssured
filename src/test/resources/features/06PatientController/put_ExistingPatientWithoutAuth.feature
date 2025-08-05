@ExistingPatientNoAuth
Feature: Put Operation [Add New Reports with/without Vitals for existing Patient]

Background: Set no auth

@ExistingPatientNoAuth_001
Scenario Outline: Check dietician able to add new reports for existing patient with valid data
Given Dietician creates PUT request by entering valid data into the form-data key and value fields and valid patient ID for existing patient with "<scenarioName>"
When Dietician send PUT http request with endpoint for existing patient with "<scenarioName>"
Then Dietician recieves 401 unauthorized for existing patient 

	
		Examples:
		| scenarioName                              |
		| Existing patient with invalid token         |