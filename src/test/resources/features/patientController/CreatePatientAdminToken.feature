Feature: Post Operation [create patient]

Background: Set admin bearer token

Scenario Outline: Check admin able to create patient with valid data and admin token
Given Admin creates POST request by entering valid data into the form-data key and value fields with "<testCaseID>"
When Admin send POST http request with endpoint to create patient with "<testCaseID>"
Then Admin recieves 403 forbidden after create patient request

			Examples:
		| testCaseID |
		| p2         |
