@CreatePatientNoAuth
Feature: Post Operation [create patient]

Background: Set no auth

@CreatePatientNoAuth_001
Scenario Outline: Check dietician able to create patient with valid data
Given Dietician creates POST request by entering valid data into the form-data key and value fields with "<scenarioName>".
When Dietician send POST http request with endpoint to create patient with "<scenarioName>"
Then Dietician recieves 401 unauthorized after create patient request

		Examples:
		| scenarioName                              |
		| Create patient with invalid token         |