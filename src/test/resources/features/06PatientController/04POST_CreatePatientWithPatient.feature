@CreatePatientWithToken
Feature: Post Operation [create patient]

Background: Generate and set bearer token before create patient
Scenario Outline: Set Patient Bearer token
Given Set Bearer Token with "<tokenName>"
	Examples:
	| tokenName|
	| patient_token |

@CreatePatientWithToken_001
Scenario Outline: Check patient able to create patient with valid data and patient token
Given Patient creates POST request by entering valid data into the form-data key and value fields with "<scenarioName>"
When Patient send POST http request with endpoint to create patient with "<scenarioName>"
Then Patient recieves 403 forbidden after create patient request

		Examples:
		| scenarioName                              |
		| Create patient with invalid token         |
	