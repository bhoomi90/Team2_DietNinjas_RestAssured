@UpdatePatientWithToken
Feature: Put Operation [update patient]

Background: Generate and set bearer token before update patient
#Scenario Outline: Set Patient Bearer token
#Given Set Bearer Token with "<tokenName>"
#	Examples:
#	| tokenName|
#	| patient_token |

@UpdatePatientWithToken_001
Scenario Outline: Check patient able to update patient with valid data and patient token
Given Patient creates PUT request by entering valid data into the form-data key and value fields with "<scenarioName>".
When Patient send PUT http request with endpoint to update patient
Then Patient recieves 403 forbidden after update patient request
			Examples:
		| scenarioName                      |
		| Update Patient with invalid token |
		