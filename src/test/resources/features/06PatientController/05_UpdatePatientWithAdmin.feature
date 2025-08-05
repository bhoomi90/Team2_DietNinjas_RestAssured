@UpdatePatientWithAdmin
Feature: Put Operation [update patient]

Background: Generate and set bearer token before update patient
#Scenario Outline: Set Admin Bearer token
#Given Set Bearer Token with "<tokenName>"
#	Examples:
#	| tokenName|
#	| admin_token |

@UpdatePatientWithAdmin_001
Scenario Outline: Check admin able to update patient with valid data and admin token
Given Admin creates PUT request by entering valid data into the form-data key and value fields with "<scenarioName>".
When Admin send PUT http request with endpoint to update patient
Then Admin recieves 403 forbidden after update patient request
			Examples:
		| scenarioName                      |
		| Update Patient with invalid token |