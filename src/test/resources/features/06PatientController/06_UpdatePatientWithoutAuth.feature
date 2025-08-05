@UpdatePatientNoAuth
Feature: Put Operation [update patient]

Background: Set no auth

@UpdatePatientNoAuth_001
Scenario Outline: Check dietician able to update patient with valid data
Given Dietician generate PUT request by entering valid data into the form-data key and value fields with "<scenarioName>".
When Dietician send PUT http request with endpoint to update patient
Then Dietician recieves 401 unauthorized after update patient request
			Examples:
		| scenarioName                      |
		| Update Patient with invalid token |